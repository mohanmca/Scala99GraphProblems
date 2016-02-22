package org.s99.eightyToNinty

abstract class GraphBase[T, U] {
  case class Edge(n1 : Node, n2 : Node, value : U) {
    def toTuple = (n1.value, n2.value, value)
  }
  case class Node(value : T) {
    var adj : List[Edge] = Nil
    // neighbors are all nodes adjacent to this node.
    def neighbors : List[Node] = adj.map(edgeTarget(_, this).get)
  }

  var nodes : Map[T, Node] = Map()
  var edges : List[Edge] = Nil

  // If the edge E connects N to another node, returns the other node,
  // otherwise returns None.
  def edgeTarget(e : Edge, n : Node) : Option[Node]

  override def equals(o : Any) = o match {
    case g : GraphBase[_, _] => (nodes.keys.toList.diff(g.nodes.keys.toList) == Nil && edges.map(_.toTuple).diff(g.edges.map(_.toTuple)) == Nil)
    case _ => false
  }

  override def toString = {
    val strNodes = nodes.map { case (k, v) => k }.toList.mkString(",")
    val strEdges = edges.mkString(", ")

    s"[ N - ${strNodes}, E - ${strEdges} ]"
  }

  def addNode(value : T) = {
    val n = new Node(value)
    nodes = Map(value -> n) ++ nodes
    n
  }

  def toTermForm : (List[T], List[(T, T, U)]) = {
    val _nodes = nodes.map { case (k, v) => k }.toList
    val _edges = edges.map { case Edge(Node(n1), Node(n2), v) => (n1, n2, v) }.toList
    (_nodes, _edges)
  }

  def findTargetEdges(n : Node) : List[(T, U)] = {
    edges.filter { edge => n == edge.n1 }.map { edge => (edge.n2.value, edge.value) }.toList
  }

  def toAdjacentForm : List[(T, List[(T, U)])] = {
    //nodes.values.map { node => (node.value, findTargetEdges(node)) }.filter(connected => connected._2.length > 0).toList
    nodes.values.map { node => (node.value, findTargetEdges(node)) }.toList
  }

}

class Graph[T, U] extends GraphBase[T, U] {
  override def equals(o : Any) = o match {
    case g : Graph[_, _] => super.equals(g)
    case _ => false
  }
  def edgeTarget(e : Edge, n : Node) : Option[Node] =
    if (e.n1 == n) Some(e.n2)
    else if (e.n2 == n) Some(e.n1)
    else None

  def addEdge(n1 : T, n2 : T, value : U) = {
    val e = new Edge(nodes(n1), nodes(n2), value)
    edges = e :: edges
    nodes(n1).adj = e :: nodes(n1).adj
    nodes(n2).adj = e :: nodes(n2).adj
  }

}

class Digraph[T, U] extends GraphBase[T, U] {
  override def equals(o : Any) = o match {
    case g : Digraph[_, _] => super.equals(g)
    case _ => false
  }

  def edgeTarget(e : Edge, n : Node) : Option[Node] =
    if (e.n1 == n) Some(e.n2)
    else None

  def addArc(source : T, dest : T, value : U) = {
    val e = new Edge(nodes(source), nodes(dest), value)
    edges = e :: edges
    nodes(source).adj = e :: nodes(source).adj
  }

}

abstract class GraphObjBase {
  type GraphClass[T, U]
  def addLabel[T](edges : List[(T, T)]) = edges.map(v => (v._1, v._2, ()))
  def term[T](nodes : List[T], edges : List[(T, T)]) = termLabel(nodes, addLabel(edges))
  def termLabel[T, U](nodes : List[T], edges : List[(T, T, U)]) : GraphClass[T, U]
  def addAdjacentLabel[T](nodes : List[(T, List[T])]) = nodes.map(a => (a._1, a._2.map((_, ()))))
  def adjacent[T](nodes : List[(T, List[T])]) = adjacentLabel(addAdjacentLabel(nodes))
  def adjacentLabel[T, U](nodes : List[(T, List[(T, U)])]) : GraphClass[T, U]
}

object Graph extends GraphObjBase {

  type GraphClass[T, U] = Graph[T, U]

  val arcPattern = "([a-z]+)>?([a-z]?)/?([0-9]?),?".r

  def fromString(string : String) : Graph[String, Unit] = {
    val strGraph = string.replace("[", "").replace("]", "").split(", ")     
    //[b-c, f-c, g-h, d, f-b, k-f, h-g]
    val nodes = strGraph.flatMap { x => x.split("-") }.foldLeft(Nil: List[String])((list, elem) => if (list.contains(elem)) list else elem :: list).reverse
    println(nodes.mkString(", "))
    val edges = strGraph.filter { _.contains("-") }.map { edge => edge.split("-") }.map { nodes => (nodes(0), nodes(1), ()) }.toList
    termLabel(nodes, edges)
  }

  def termLabel[T, U](nodes : List[T], edges : List[(T, T, U)]) : Graph[T, U] = {
    val g = new Graph[T, U]
    nodes.map(g.addNode)
    edges.map(v => g.addEdge(v._1, v._2, v._3))
    g
  }

  def adjacentLabel[T, U](nodes : List[(T, List[(T, U)])]) = {
    val g = new Graph[T, U]
    for ((v, a) <- nodes) g.addNode(v)
    for ((n1, a) <- nodes; (n2, l) <- a) {
      if (!g.nodes(n1).neighbors.contains(g.nodes(n2)))
        g.addEdge(n1, n2, l)
    }
    g
  }
}

object Digraph extends GraphObjBase {
  type GraphClass[T, U] = Digraph[T, U]

  val arcPattern = "([a-z]+)>?([a-z]?)/?([0-9]?),?".r

  def collectNodes(accumulator : List[String], edges : List[String]) : List[String] = edges match {
    case List(node) => if (accumulator.contains(node)) accumulator else node :: accumulator;
    case List(node, targetNode) => collectNodes(collectNodes(accumulator, List(node)), List(targetNode))
    case List(node, targetNode, _) => collectNodes(collectNodes(accumulator, List(node)), List(targetNode))
  }

  def fromStringLabel(string : String) : Digraph[String, Int] = {
    val nodesAndEdges = arcPattern.findAllIn(string).matchData.map { x => x.subgroups.filter { _.size > 0 } }.toList
    val nodes = nodesAndEdges.foldLeft(Nil : List[String])(collectNodes).reverse
    val edges = nodesAndEdges.filter { x => x.length == 3 }.map { case List(n1, n2, v) => (n1, n2, v.toInt) }
    termLabel(nodes, edges)
  }

  def termLabel[T, U](nodes : List[T], edges : List[(T, T, U)]) = {
    val g = new Digraph[T, U]
    nodes.map(g.addNode)
    edges.map(v => g.addArc(v._1, v._2, v._3))
    g
  }
  def adjacentLabel[T, U](nodes : List[(T, List[(T, U)])]) = {
    val g = new Digraph[T, U]
    for ((n, a) <- nodes) g.addNode(n)
    for ((s, a) <- nodes; (d, l) <- a) g.addArc(s, d, l)
    g
  }
}


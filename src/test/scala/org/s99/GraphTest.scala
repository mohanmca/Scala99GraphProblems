package org.s99

import org.scalatest.FlatSpec
import org.s99.graph.Graph
import org.s99.graph.Digraph

package graph {
  class GraphTest extends FlatSpec {
    "Single edge graph" should "have 1 edge" in {
      val nodes = List("a", "b")
      val edges = ("a", "b", 4) :: Nil
      val singleEdgeGraph = Graph.termLabel(nodes, edges)
      assert(singleEdgeGraph.edges.length == 1)
    }

    ignore should "have termForm " in {
      val nodes = List("a", "b")
      val edges = ("a", "b", ()) :: Nil
      val singleEdgeGraphInTermForm = Graph.termLabel(nodes, edges).toTermForm
      //TODO: order of node matters now, handle it
      val expectedGraphInTermForm : (List[String], List[(String, String, Unit)]) = (List("b", "a"), List(("a", "b", ())))
      assert(singleEdgeGraphInTermForm == expectedGraphInTermForm)
    }

    ignore should "have multiple termForm " in {
      val nodes = List("a", "b")
      val edges = ("a", "b", ()) :: Nil
      val graphInTermForm = Graph.fromString("[b-c, f-c, g-h, d, f-b, k-f, h-g]").toTermForm
      //TODO: order of node matters now, handle it
      val expectedTermForm : (List[String], List[(String, String, Unit)]) = (List("d", "k", "h", "c", "f", "g", "b"), List(("h", "g", ()), ("k", "f", ()), ("f", "b", ()), ("g", "h", ()), ("f", "c", ()), ("b", "c", ())))
      assert(graphInTermForm == expectedTermForm)
    }

    "Digraph" should "be able to parse from String" in {
      val node = "[p>q/9, m>q/7, k, p>m/5]"
      val digraph = Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]")
      assert(digraph.toTermForm == (List("k", "m", "q", "p"), List(("p", "m", 5), ("m", "q", 7), ("p", "q", 9))))
    }

  }
}
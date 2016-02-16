import org.s99.graph.Digraph

object GraphTeset {
  val digraph = Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]")
                                                  //> digraph  : org.s99.graph.Digraph[String,Int] = [ N - k,m,q,p, E - Edge(Node(
                                                  //| p),Node(m),5), Edge(Node(m),Node(q),7), Edge(Node(p),Node(q),9) ]
  val t = digraph.toString()                      //> t  : String = [ N - k,m,q,p, E - Edge(Node(p),Node(m),5), Edge(Node(m),Node(
                                                  //| q),7), Edge(Node(p),Node(q),9) ]
  val a=  digraph.toTermForm                      //> a  : (List[String], List[(String, String, Int)]) = (List(k, m, q, p),List((p
                                                  //| ,m,5), (m,q,7), (p,q,9)))
 
 digraph.toAdjacentForm                           //> res0: List[(String, List[(String, Int)])] = List((m,List((q,7))), (p,List((m
                                                  //| ,5), (q,9))))
}
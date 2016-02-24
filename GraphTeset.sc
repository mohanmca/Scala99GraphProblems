package org.s99.eightyToNinty


object GraphTeset {
  val digraph = Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]")
                                                  //> digraph  : org.s99.eightyToNinty.Digraph[String,Int] = [ N - k,m,q,p, E - Ed
                                                  //| ge(Node(p),Node(m),5), Edge(Node(m),Node(q),7), Edge(Node(p),Node(q),9) ]
  val t = digraph.toString()                      //> t  : String = [ N - k,m,q,p, E - Edge(Node(p),Node(m),5), Edge(Node(m),Node(
                                                  //| q),7), Edge(Node(p),Node(q),9) ]
  val a=  digraph.toTermForm                      //> a  : (List[String], List[(String, String, Int)]) = (List(k, m, q, p),List((p
                                                  //| ,m,5), (m,q,7), (p,q,9)))
 
                                                 
 Digraph.fromStringLabel("[p>q/9, m>q/7, k, p>m/5]").findPaths("p", "q")
                                                  //> res0: List[List[String]] = List(List(p, m, q), List(p, q))
 
}
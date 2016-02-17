import org.s99.graph.Digraph
import org.s99.one_to_ten.oneToTen

object GraphTeset {
  val t = oneToTen.last(List(1, 2))               //> t  : Int = 2
  val t1 = oneToTen.last(List(1))                 //> t1  : Int = 1
  val a = oneToTen.penultimate(List(1, 2))        //> a  : Int = 1
  val b = oneToTen.penultimate(List(1, 2, 3))     //> b  : Int = 2
  val c = oneToTen.nth(2, List(1, 1, 2, 3, 4))    //> c  : Int = 2
  val d = oneToTen.depleteHeadDups(List('a', 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
                                                  //> d  : (List[Any], List[Any]) = (List(a),List('a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 
                                                  //| 'd, 'e, 'e, 'e, 'e))
  val s = oneToTen.sameElement(List(1, 2))        //> s  : Boolean = false

  val p = oneToTen.pack(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))
                                                  //> p  : List[List[Char]] = List(List(a, a, a, a), List(b), List(c, c), List(a, 
                                                  //| a), List(d), List(e, e, e, e))

}
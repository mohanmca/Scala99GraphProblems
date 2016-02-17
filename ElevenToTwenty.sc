import org.s99.graph.Digraph
import org.s99.one_to_ten.elevenToTwenty

object GraphTeset {

    val t = elevenToTwenty.combinations(3, List('a', 'b', 'c', 'd'))
                                                  //> t  : List[List[Char]] = List(List(a, b, c), List(a, b, d), List(a, c, d), Li
                                                  //| st(b, c, d))
}

import org.s99.oneToTen.ListFunctions
import org.s99.fourtyToFifty.PrimeNumbers

object PrimeNumbersTest {

  PrimeNumbers.goldbach(26)                       //> res0: List#827136[List#827136[Int#1088]] = List(List(3, 23), List(7, 19))
  PrimeNumbers.printGoldbachList(9 to 20)         //> List(3, 7)List(5, 7)List(3, 11)List(3, 13)List(5, 11)List(5, 13)List(7, 11)L
                                                  //| ist(3, 17)List(7, 13)
  PrimeNumbers.printGoldbachListLimited(1 to 2000, 50)
                                                  //> List(53, 59)List(53, 61)List(53, 67)List(59, 61)List(53, 71)List(53, 73)List
                                                  //| (59, 67)List(61, 67)List(59, 71)List(53, 79)List(59, 73)List(61, 71)List(61,
                                                  //|  73)List(53, 83)List(59, 79)List(67, 71)List(61, 79)List(67, 73)List(53, 89)
                                                  //| List(59, 83)List(61, 83)List(71, 73)List(67, 79)List(59, 89)List(53, 97)List
                                                  //| (61, 89)List(67, 83)List(71, 79)List(73, 79)List(53, 101)List(71, 83)List(53
                                                  //| , 103)List(59, 97)List(67, 89)List(73, 83)List(61, 97)List(53, 107)List(59, 
                                                  //| 101)List(71, 89)List(53, 109)List(59, 103)List(61, 101)List(73, 89)List(79, 
                                                  //| 83)List(61, 103)List(67, 97)List(53, 113)List(59, 107)List(59, 109)List(61, 
                                                  //| 107)List(67, 101)List(71, 97)List(79, 89)List(61, 109)List(67, 103)List(73, 
                                                  //| 97)List(59, 113)List(71, 101)List(83, 89)List(61, 113)List(67, 107)List(71, 
                                                  //| 103)List(73, 101)List(67, 109)List(73, 103)List(79, 97)List(71, 107)List(53,
                                                  //|  127)List(67, 113)List(71, 109)List(73, 107)List(79, 101)List(83, 97)List(73
                                                  //| , 109)List(79, 103)List(
                                                  //| Output exceeds cutoff limit.|
                                                 
}
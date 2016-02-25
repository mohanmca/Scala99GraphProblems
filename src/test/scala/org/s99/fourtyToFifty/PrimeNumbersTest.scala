package org.s99.fourtyToFifty

import org.scalatest.FlatSpec

class PrimeNumbersTest extends FlatSpec {
  "isPrime" should "be true for primeNumber" in {
    assert(PrimeNumbers.isPrime(23) == true)
  }

  it should "genrate sequence of goldbach" in {
    var expected = List(List(3, 7), List(5, 7), List(3, 11), List(3, 13), List(5, 11), List(5, 13), List(7, 11), List(3, 17), List(7, 13))
    var actual = PrimeNumbers.printGoldbachList(9 to 20)
    assert(actual == expected)
  }
}

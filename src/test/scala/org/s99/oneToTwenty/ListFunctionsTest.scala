package org.s99.oneToTwenty

import org.scalatest.FlatSpec

class ListFunctionsTest extends FlatSpec {

  "List with few elements" should "have penultimate" in {
    assert(ListFunctions.penultimate(List(1, 2)) == 1)
  }

  it should "have pack" in {
    val actualPack = ListFunctions.pack(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))
    val expected = List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e'))
    assert(expected == actualPack)
  }

  it should "have encode" in {
    val actualPack = ListFunctions.encode(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))
    val expected = List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e'))
    assert(expected == actualPack)
  }

  it should "have encodeModified" in {
    val actualPack = ListFunctions.encodeModified(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))
    val expected = List((4, 'a'), 'b', (2, 'c'), (2, 'a'), 'd', (4, 'e'))
    assert(expected == actualPack)
  }

  it should "have insert" in {
    val actual = ListFunctions.insertAt('e', 1, List('a', 'b', 'c', 'd'))
    val expected = List('a', 'e', 'b', 'c', 'd')
    assert(expected == actual)
  }

  it should "have combination" in {
    val actual = ListFunctions.combinations(3, List('a', 'b', 'c', 'd'))
    val expected = List(List('a', 'b', 'c'), List('a', 'b', 'd'), List('a', 'c', 'd'), List('b', 'c', 'd'))
    assert(expected == actual)
  }

}
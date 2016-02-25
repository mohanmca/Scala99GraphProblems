package org.s99.fourtyToFifty

import org.s99.oneToTwenty.ListFunctions;

import scala.collection.immutable.Seq

object PrimeNumbers {
  def isPrime(n : Int) = {
    if (n == 1) false
    else if (n == 2 || n == 3) true;
    else {
      val q = Math.sqrt(n).toInt
      val seq = (2 to q)
      seq.filter { x => n % x == 0 }.size == 0
    }
  }

  def generatePrimeUpto(n : Int) = {
    val seq = 1 to n
    seq.filter { x => isPrime(x) }.toList
  }

  def goldbach(n : Int) : List[List[Int]] = {
    val primes = generatePrimeUpto(n)
    ListFunctions.combinations(2, primes).filter { x => x.foldLeft(0)((a, b) => a + b) == n }
  }

  def printGoldbachList(n : Seq[Int]) = {
    val goldbachs = n.filter { _ % 2 == 0 }.flatMap { goldbach(_) }.toList
    println(goldbachs.mkString)
    goldbachs
  }

  def printGoldbachListLimited(n : Seq[Int], min : Int) = {
    val goldbachs = n.filter { _ % 2 == 0 }.flatMap { goldbach(_) }
    val goldbachsConstraint = goldbachs.filter { _.forall { e => e > min } }
    println(goldbachsConstraint.mkString)
    goldbachsConstraint
  }

}
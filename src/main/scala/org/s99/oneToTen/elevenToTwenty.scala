package org.s99.oneToTen

object elevenToTwenty {
  def combinations_[T](n : Int, list : List[T]) : List[List[T]] = n match {
    case 1 => list.map(x => List(x))
    case x if x > 1 => list.zipWithIndex.flatMap { case (e, i) => combinations_(x - 1, list.filter(_ != e)).map { sublist => e :: sublist } }
  }

  def combinations[T](n : Int, list : List[T])(implicit ord : Ordering[T]) : List[List[T]] = combinations_(n, list).map { _.sorted(ord) }.distinct

}
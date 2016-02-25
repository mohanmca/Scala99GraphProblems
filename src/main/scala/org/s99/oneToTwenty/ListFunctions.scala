package org.s99.oneToTwenty

object ListFunctions {
  def last[T](list : List[T]) : T = list match {
    case head :: Nil => head
    case head :: next => last(next)
  }
  def penultimate[T](list : List[T]) : T = list match {
    case head :: penultimate :: Nil => head
    case head :: next => penultimate(next)
  }

  def nth[T](n : Int, list : List[T]) : T = {
    if (n == 0)
      list.head
    else
      nth(n - 1, list.tail)
  }

  def dropWhile[T](list : List[T], predicate : T => Boolean) : List[T] = list match {
    case Nil => Nil
    case head :: next => if (predicate(head)) dropWhile(next, predicate) else list
  }

  def sameElement[T](list : List[T]) : Boolean = dropWhile(list, (x : T) => x == list.head).length == 0

  def collectWhile[T](list : List[T], predicate : T => Boolean) : (List[T], List[T]) = list match {
    case Nil => (Nil, Nil)
    case head :: next => {
      if (predicate(head))
        (head :: collectWhile(next, predicate)._1, collectWhile(next, predicate)._2)
      else (Nil, list)
    }
  }

  def depleteHeadDups[T](list : List[T]) : (List[T], List[T]) = {
    val predicate : T => Boolean = x => x == list.head
    collectWhile(list, predicate)
  }

  def combinations_[T](n : Int, list : List[T]) : List[List[T]] = n match {
    case 1 => list.map(x => List(x))
    case x if x > 1 => list.zipWithIndex.flatMap { case (e, i) => combinations_(x - 1, list.filter(_ != e)).map { sublist => e :: sublist } }
  }

  def combinations[T](n : Int, list : List[T])(implicit ord : Ordering[T]) : List[List[T]] = combinations_(n, list).map { _.sorted(ord) }.distinct

  def pack[T](list : List[T]) : (List[List[T]]) = depleteHeadDups(list) match {
    case (f, Nil) => List(f)
    case (f, d) => f :: pack(d)
  }

  def encode[T](list : List[T]) : List[(Int, T)] = pack(list).map(xs => (xs.length, xs.head)).toList

  def encodeModified[T](list : List[T]) : List[Any] = encode(list) map {
    _ match {
      case a @ (n, x) if n > 1 => a
      case a @ (n, x) if n == 1 => x
    }
  }

  def insertAt[T](elem : T, n : Int, list : List[T]) : List[T] = n match {
    case 0 => elem :: list
    case x if x > 0 => list.head :: insertAt(elem, x - 1, list.tail)
  }

}
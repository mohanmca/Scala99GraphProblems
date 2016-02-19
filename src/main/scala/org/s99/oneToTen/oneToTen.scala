package org.s99.oneToTen

object oneToTen {
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

  def pack[T](list : List[T]) : (List[List[T]]) = depleteHeadDups(list) match {
    case (f, Nil) => List(f)
    case (f, d) => f :: pack(d)
  }

}
package org.s99

import org.scalatest.FlatSpec
import org.s99.graph.Graph

class GraphTest extends FlatSpec {
  "Single edge graph" should "have 1 edge" in {
    val node = List("a", "b")
    val edge = ("a", "b", 4)
    val singleEdgeGraph = Graph.termLabel(node, edge :: Nil)
    assert(singleEdgeGraph.edges.length == 1)
  }

}
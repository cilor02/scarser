package com.milo.scala.quiz.node

import scala.collection.mutable.Map

class LeafVarNode (name:String)(implicit var map :Map[String,Node]) extends Node {
def value = { map.get(name).get.value}
}
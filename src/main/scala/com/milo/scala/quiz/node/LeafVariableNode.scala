package com.milo.scala.quiz.node

import scala.collection.mutable.Map

class LeafVariableNode (name:String)(implicit var map :Map[String,Node], var variableMap :Map[String,Double]) extends Node {

  def value = variableMap.get(name).get
}
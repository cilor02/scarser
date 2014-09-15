package com.milo.scala.quiz.node

import scala.collection.mutable.Map

case class BooleanRefNode (ref:String)(implicit nodeMap :Map[String,BooleanNode]) extends BooleanNode{
override def value = {(nodeMap.get(ref).get).value}
}
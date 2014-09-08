package com.milo.scala.quiz.node

import scala.collection.mutable.Map

case class BooleanRefNode (ref:String)(implicit nodeMap :Map[String,BooleanNode]) extends BooleanNode{
def value = {println("BooleanRefNode:" + ref);nodeMap.get(ref).get.value}
}
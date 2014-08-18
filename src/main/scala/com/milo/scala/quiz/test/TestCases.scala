package com.milo.scala.quiz.test

import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import com.milo.scala.quiz.node.LeafNumericNode
import scala.collection.mutable.Map

object TestCases extends App {
implicit var map:Map[String,Node] =  Map[String,Node]()
implicit var variableMap:Map[String,Double] = Map[String,Double]()
map += ("com.milo.var1" -> new LeafNumericNode(16))
map += ("17" -> new LeafNumericNode(17))

val node = new BinaryNode("+","17","com.milo.var1")
println(node.value)



}
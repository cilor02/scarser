package com.milo.scala.quiz.node

import scala.collection.mutable.Map

 class BinaryNode (op:String,left:String,right:String)(implicit var map:Map[String,Node], var variableMap:Map[String, Double]) extends Node
{
  
  val leftNode  = Node.buildNode(left)
  val rightNode = Node.buildNode(right)
  

  

  
  val operation = op match 
  {
    case "+"  => (a:Double,b:Double) => a+b
    case "-"  => (a:Double,b:Double) => a-b
    case "*"  => (a:Double,b:Double) => a*b
    case "/"  => (a:Double,b:Double) => a/b
    case "**"  => (a:Double,b:Double) => math.pow(a, b)
  }
  
  def value = operation(leftNode.value,rightNode.value)
  
}
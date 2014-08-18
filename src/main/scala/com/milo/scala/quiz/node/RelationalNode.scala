package com.milo.scala.quiz.node

class RelationalNode (op:String,leftNode:String,rightNode:String)(implicit map :Map[String,Node]) extends BooleanNode
{

  
  val left:Node = null
  val right:Node = null
  
  
  val operation = op match 
  {
    case "=" => (a:Double,b:Double) => a == b
    case "!=" => (a:Double,b:Double) => a != b
    case "<" => (a:Double,b:Double) => a < b
    case ">" => (a:Double,b:Double) => a > b
    case ">=" => (a:Double,b:Double) => a >= b   
    case "<=" => (a:Double,b:Double) => a <= b 
  }
  
  override def value = operation(left.value,right.value)
  
  override def toString = leftNode + ":" + op + ":" +  rightNode
}
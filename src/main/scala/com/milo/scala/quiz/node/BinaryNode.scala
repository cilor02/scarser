package com.milo.scala.quiz.node

import scala.collection.mutable.Map

 class BinaryNode (op:String,left:String,right:String)(implicit var map:Map[String,Node], var variableMap:Map[String, Double]) extends Node
{
  
  val leftNode  = buildNode(left)
  val rightNode = buildNode(right)
  
def isNumeric(str: String): Boolean = {
  !throwsNumberFormatException(str.toLong) || !throwsNumberFormatException(str.toDouble)
}
  
def isReallyNumeric(s:String) ={ 
  val digits = (0 to 9 toList) map (_ toString) 
  s.toList map ( _ toString) forall (digits contains(_))
}
 
def throwsNumberFormatException(f: => Any): Boolean = {
  try { f; false } catch { case e: NumberFormatException => true }
}
  
def isAllDigits(x: String) = x forall Character.isDigit
 
  def buildNode(s:String) :Node =
      s match
  { 
    case x if (s.head=='+' || s.head=='-') && s.tail.forall( _.isDigit) =>new LeafNumericNode(s.toInt)
    case x if (s.head=='+' || s.head=='-') && isReallyNumeric(s.tail) => new LeafNumericNode(s.toDouble)
    case x if isReallyNumeric(s) =>  new LeafNumericNode(Integer.parseInt(s)) //leading sign ?
    case _ => variableMap.get(s) match { case None =>  new LeafVarNode(s) case _ => new LeafVariableNode(s) }  
  }
  

  
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
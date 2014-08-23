package com.milo.scala.quiz.node

import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.parser.ExpressionBuilder
import scala.collection.mutable.Map

class RelationalNode (op:String,leftExp:String,rightExp:String)(implicit var map :Map[String,Node], var variableMap :Map[String,Double]) extends BooleanNode
{
  
var tokeniserR = new ExpressionTokeniser(rightExp)
val nodeR = new ExpressionBuilder(tokeniserR.startTokenising.toList)
val tokensR = nodeR.processNodes
val right:Node = new LeafVarNode(tokensR.head)
    
var tokeniserL = new ExpressionTokeniser(leftExp)
val nodeL = new ExpressionBuilder(tokeniserL.startTokenising.toList) 
val tokensL = nodeL.processNodes
val left:Node = new LeafVarNode(tokensL.head)  
  
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
  
  override def toString = leftExp + ":" + op + ":" +  rightExp
}
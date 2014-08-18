package com.milo.scala.quiz.parser

import scala.collection.mutable.Map

class ExpressionBuilder (exp:List[String])(implicit var map:Map[String,com.milo.scala.quiz.node.Node], var variableMap : Map [String,Double])
{
  def processBrackets(expression:List[String]):List[String] =
  {
        val endBracket = expression.indexOf(")")
       if( endBracket > -1) 
       {
    val startBracket = expression.lastIndexOf("(", endBracket)
    val expTree = new NoBracketExpressionBuilder(expression.slice(startBracket + 1,endBracket))
    expTree.process
 processBrackets ((expression splitAt (startBracket)_1):::expTree.newList:::((expression splitAt (startBracket)_2).drop(endBracket - (startBracket -1)))) 
       }
       else
         expression
  }
  def process():List[String] = 
  {    
     processBrackets(exp)
  }  
  
  
}
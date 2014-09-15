package com.milo.scala.quiz.node

import scala.collection.mutable.Map


class BinaryPairNode (op:String,leftBoolToken:String,rightBoolToken:String)(implicit var nodeMap:Map[String, BooleanNode], var map :Map[String,Node], var variableMap :Map[String,Double]) extends BooleanNode
{
  val arithOps = List("<=",">=","=",">","<", "factorOf", "multipleOf")
  val boolOps = List("or","and")
  
        def stripOuterBrackets(s:String,p:Int):Boolean =
      {
        val sTrim = s.trim
        if (sTrim.length == 0)
          false
        else
         sTrim.charAt(0) match 
        {
          case ')' if sTrim.length() > 1 && p == 1  => false
          case ')' if sTrim.length() == 1 && p > 0  => true
          case '(' => stripOuterBrackets(sTrim.substring(1), p + 1) 
          case ')' => stripOuterBrackets(sTrim.substring(1), p - 1) 
          
          case _ => stripOuterBrackets(sTrim.substring(1), p )

        }
      }
  
          val leftToken = if(stripOuterBrackets(leftBoolToken.trim(), 0))
            leftBoolToken.substring(1, leftBoolToken.length() - 1)
         else
           leftBoolToken
           
         val rightToken = if(stripOuterBrackets(rightBoolToken.trim(), 0))
            rightBoolToken.substring(1, rightBoolToken.length() - 1)
         else
           rightBoolToken
           
  
  val leftRelational:BooleanNode = buildNode(leftToken)
  val rightRelational:BooleanNode = buildNode(rightToken)
  
  
  val operation = op match 
  {
    case "and" => (a:Boolean,b:Boolean) => a&&b
    case "or" => (a:Boolean,b:Boolean) => a||b
        
  }
  def buildNode(s:String):BooleanNode =
  {
    //println("buildNode  ------" + s )
    s match 
    {
      case x if s.equalsIgnoreCase("true") => TrueNode()
      case x if s.equalsIgnoreCase("false") => FalseNode()
      case _ =>    buildNodes(s,arithOps)
    }
  }
  /*

  */
  def buildNodes(s:String, ops:List[String]):BooleanNode=
    {
    // possibly a ref to a composite sub phrase
      if (ops.isEmpty)
      {
       // println("*********************" + s)
         return nodeMap.get{s} match{
          case Some(n) => n
          //case None => Nil
      }
       
      }
      val leftExpAndRightExp = s.split(ops.head)
      if(leftExpAndRightExp.length < 2)
        buildNodes(s, ops.tail)
      else
        new RelationalNode(ops.head,leftExpAndRightExp(0),leftExpAndRightExp(1))
     }
  
  
  override def value = operation(leftRelational.value,rightRelational.value)
  
  override def toString = leftBoolToken + ":" + op + ":" +  rightBoolToken
  

}
package com.milo.scala.quiz.parser

import com.milo.scala.quiz.node.Node
import com.milo.scala.quiz.node.BinaryNode
import scala.collection.mutable.Map
import com.milo.scala.quiz.node.LeafNumericNode



class NoBracketExpressionBuilder (tokens:List[String])(implicit var map:Map[String,Node], implicit var variableMap:Map[String, Double])
{

  
  val operators = List ("**","/","*","+","-")
  var newList:List[String] = tokens
  def process :Unit =
  {
    if(tokens.size == 1)
    {
      val n = Node.buildNode(tokens.head)
      val name = NoBracketExpressionBuilder.newName
      map += (name -> n)
      //n.store(name)
      newList = List(name)
    }else
    {
      operators.foreach(processOp(_))
    }
  }
  
   def processOp (op:String) :Unit = 
   {
    newList = processAllOccsOps(newList,op)
   }
  
  def processAllOccsOps (lstTkns:List[String], op:String):List[String] =
  { 
    val i = lstTkns.indexWhere(s =>s.==(op))
    if (i >= 0)
    {
      val n = new BinaryNode(lstTkns(i),lstTkns(i-1),lstTkns(i+1))
      
      val name = NoBracketExpressionBuilder.newName
      map += (name -> n)
      //n.store(name)
      lstTkns.take(i-1):::processAllOccsOps(name::(lstTkns drop i+2),op)
    }else
    {
      
      lstTkns
    }
  }
}

object NoBracketExpressionBuilder
{
    var k:Int = 0
  def newName():String = {k += 1; "com_milo_var" + k}
    
}
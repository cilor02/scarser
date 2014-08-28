package com.milo.scala.quiz.node

import scala.collection.mutable.Map

trait Node 
{
  def value :Double
  //def store(s:String) :Unit = Node.map.+=(s->this)
  //def retrieve (s:String) = Node.map.get(s).get
  //def getMap :Map[String,Node] = Node.map
}

object Node 
{

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
 
  def buildNode(s:String) (implicit map : Map[String,Node], variableMap : Map[String,Double]):Node =
      s match
  { 
    case x if (s.head=='+' || s.head=='-') && s.tail.forall( _.isDigit) =>new LeafNumericNode(Integer.parseInt(s))
    case x if (s.head=='+' || s.head=='-') && isReallyNumeric(s.tail) => new LeafNumericNode(Integer.parseInt(s))
    case x if isReallyNumeric(s) =>  new LeafNumericNode(Integer.parseInt(s)) //leading sign ?
    case _ => variableMap.get(s) match { case None =>  new LeafVarNode(s) case _ => new LeafVariableNode(s) }  
  }
  
  //var map:Map[String,Node] = Map()
  //val variableMap : Map [String,AnyVal] = Map()
  //def loadVariable(varName:String, numValue:AnyVal ):Unit = {variableMap.+(varName-> numValue)} 
}
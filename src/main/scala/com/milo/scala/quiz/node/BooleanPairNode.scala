package com.milo.scala.quiz.node


class BinaryPairNode (op:String,leftNode:String,rightNode:String) extends BooleanNode
{

  
  val left:BooleanNode = buildNode(leftNode)
  val right:BooleanNode = buildNode(rightNode)
  
  
  val operation = op match 
  {
    case "and" => (a:Boolean,b:Boolean) => a&&b
    case "or" => (a:Boolean,b:Boolean) => a||b
        
  }
  def buildNode(s:String):BooleanNode =
  {
    s match 
    {
      case x if s.equalsIgnoreCase("true") => TrueNode()
      case x if s.equalsIgnoreCase("false") => FalseNode()
      
    }
  }
  override def value = operation(left.value,right.value)
  
  override def toString = leftNode + ":" + op + ":" +  rightNode
}
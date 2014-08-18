package com.milo.scala.parser.state

import scala.math.Numeric

trait State {
  def next(s:String):State
  
  def next(f: => String):State = next(f)
  def isOperator(s:String):Boolean = 
    s match 
    {
    case "+"|"-"|"*"|"/"|"**" => true
    case _ =>false
    }
  
    def isSign(s:String):Boolean = 
    s match 
    {
    case "+"|"-" => true
    case _ =>false
    }
    
    def isVar(s:String):Boolean = 
    {
            
    s match 
    {
    case x if(!s.head.isLetter) => false
    case x if (s.tail.filter(ch => ch.isDigit||ch.isLetter||ch == '_').length() >0) => true
    case _ =>false
    }
    }
    
    def displayEndState () = println("success")
        
        def isNumeric(str: String): Boolean = {
  !throwsNumberFormatException(str.toLong) || !throwsNumberFormatException(str.toDouble)
}
 
def throwsNumberFormatException(f: => Any): Boolean = {
  try { f; false } catch { case e: NumberFormatException => true }
}
 


}
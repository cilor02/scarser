package com.milo.scala.parser.state

import scala.collection.mutable.ListBuffer

class SignBinder (tokens:List[String])  {
  var newLst:ListBuffer[String] = ListBuffer()

  def bindSigns () :ListBuffer[String] =
  {

  val sign = """(\+|-)""".r
  val operator = """(\+|-|/|\*|\*\*)""".r
         
    def next(tokens:List[String]):ListBuffer[String] =
    {
    tokens match 
    {
      case List() => newLst
      case operator(head)::tail if head == "-" => newLst.++=( List ("+","-1","*"));signOrVarNum(tokens.tail)
      case operator(head)::tail => newLst.+=( tokens.head);signOrVarNum(tokens.tail)

      case _  => newLst.+=( tokens.head);next(tokens.tail)
    }
    }
  
    def signOrVarNum (tokens:List[String]):ListBuffer[String] =
    {
     tokens match 
    {
      case sign(head) ::tail => newLst.+=(tokens.head + tokens.tail.head); next(tokens.drop(2))
      case _  => newLst.+=( tokens.head);next(tokens.tail)
    }
    
    }
  
    signOrVarNum(tokens)
    
  }
  
 
}
package com.milo.scala.parser.state

class OperatorState extends State 
{
 def next(s:String):State = {
 s match 
 {
   case x if isVar(s) => new VarState
   case x if isNumeric(s) => new NumberState
   case x if isSign(s) => new SignState
   case x if(isOperator(s)) => new ErrorState
 }
 }
}
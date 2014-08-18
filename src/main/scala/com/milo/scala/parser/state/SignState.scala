package com.milo.scala.parser.state

class SignState extends State 
{
 def next(s:String):State = {
 s match 
 {
   case x if isNumeric(s) => new NumberState 
   case x if isVar(s) => new VarState
   case _ => new ErrorState
 }
 }
}
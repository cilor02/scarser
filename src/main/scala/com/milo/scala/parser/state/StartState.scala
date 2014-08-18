package com.milo.scala.parser.state

class StartState extends State 
{
 def next(s:String):State = {
 s match 
 {
   case x if isNumeric(s) => new NumberState
   case x if isSign(s) => new SignState
   case _ => new ErrorState
 }
 }
}
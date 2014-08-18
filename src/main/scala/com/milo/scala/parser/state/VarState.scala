package com.milo.scala.parser.state

class VarState extends State{
 def next (s:String):State =
 s match 
 {
   case null => new EndState
   case x if isOperator(s) => new OperatorState
   case _ => new ErrorState
 }
}
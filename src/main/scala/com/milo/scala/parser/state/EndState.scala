package com.milo.scala.parser.state

class EndState extends State{
  def next(s:String) :State = new StartState
  override def displayEndState () = println("end")
}
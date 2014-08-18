package com.milo.scala.parser.state

class ErrorState extends State{

  def next (s:String) :State = new StartState
  override def displayEndState () = println("error")
  
}
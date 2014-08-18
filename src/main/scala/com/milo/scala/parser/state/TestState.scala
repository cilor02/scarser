package com.milo.scala.parser.state

object TestState {
  
  def main(args:Array[String]):Unit = 
  {
    val mgr:StateManager = new StateManager(List("1.23","*","23.1","-","-","a5rt"))
    mgr.process
    mgr.state.displayEndState
  }

}
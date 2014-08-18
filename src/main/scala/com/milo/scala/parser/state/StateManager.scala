package com.milo.scala.parser.state

import scala.collection.Iterator

class StateManager(tokens:List[String]) {

  var state:State = new StartState
  val iterator:Iterator[String] = tokens.iterator
  
  def process ():Unit =
  {
    do
    state = state.next(if(iterator.hasNext)iterator.next else null)
    while(!state.isInstanceOf[ErrorState] && !state.isInstanceOf[EndState])
  }
}
package com.milo.scala.parser.state

object TestBinder
{

  
  def main(args:Array[String]): Unit = 
  {
    
    val signBinder:SignBinder = new SignBinder(List("+","2","-","-","7","-","+","abc","/","def"))
    println(signBinder.bindSigns)
  }
  
}
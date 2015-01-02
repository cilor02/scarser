package com.milo.scala.question

import scala.xml.XML
import scala.xml.Node
import scala.xml.Elem
import scala.xml.Text

class RandomQuestionSelector (rootDir : String)
{
  val testRootDir = rootDir

  
  def selectNodes (folders: String*) :Seq [Node] =
  {
      
  val files = folders.map(( new java.io.File(testRootDir ,_).listFiles())).flatten
  val files2 = folders.flatMap((new java.io.File(testRootDir ,_).listFiles()))
   folders.flatMap((new java.io.File(testRootDir ,_).listFiles())).map(XML.loadFile(_))
    
  }
  
  def xmlToLatex (n:Node):String =
  {
    n match
    {
      case e: Elem if e.label == "rules" => ""
      case e: Elem if e.label == "exp" => "$$" + e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"$$";
      case e: Elem if e.label == "bracket" => "\\left ("+ e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"\\right )";
      case e: Elem if e.label == "frac" => "\\frac"+ e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y));
      case e: Elem if e.label == "num" => "{" + e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"}";
      case e: Elem if e.label == "den" => "{" + e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"}";
      case e: Elem if e.label == "sqrt" => "\\sqrt{"+ e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"}";
      case e: Elem if e.label == "power" => "^{"+ e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"}";
      case e: Elem =>e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y))
      case e: Text =>e.text.trim()

    }
  }
  
}
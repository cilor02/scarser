package com.milo.scala.quiz.misc

import com.milo.scala.quiz.xml.QuestionProcessor
import scala.xml.transform.RewriteRule
import scala.xml.Node
import scala.xml.Elem
import scala.xml.Text


object RunQuestionCreator5 extends App
{
  override def main(args:Array[String]){
  
  val qProcessor = new QuestionProcessor()
  
  val testDoc = 
    <exp>
<frac>
 <num>
  <sqrt>5</sqrt><operation>-</operation><sqrt>3</sqrt>
 </num>
 <den> 
  <sqrt>7</sqrt><operation>-</operation><sqrt>3</sqrt>
 </den>
</frac>
</exp>
  
    
    val testDoc2 = <exp>
 <frac>
  <num><term>-b</term><operation>+</operation><sqrt>b<power>2</power> <operation>-</operation>4ac</sqrt></num><den>2a</den>
 </frac>
</exp>

   val testDoc3 = <exp>
 <term>x</term><power><frac><num>2</num><den>3</den></frac></power><operation>=</operation><term>16</term>
</exp>
     
     val testDoc4 = <exp>
<bracket>
 <frac>
  <num>9</num><den>4</den>
 </frac>
</bracket>
<power><frac><num>-3</num><den>2</den></frac></power>
</exp>
    
       val testDoc5 = <exp>
<bracket>
 <frac>
  <num><term>s</term><power><frac><num>9</num><den>10</den></frac></power></num><den><term>s</term><power><frac><num>2</num><den>3</den></frac></power></den>
 </frac>
</bracket>
<power><frac><num>-3</num><den>2</den></frac></power>
</exp>
    def processXml (n:Node)
  {
    n match
    {
      case e: Elem if e.label == "exp" => print("$");e.child.map (processXml  (_)); print("$");
      case e: Elem if e.label == "bracket" => print("\\left (");e.child.map (processXml  (_));print("\\right )");
      case e: Elem if e.label == "frac" => print("\\frac");e.child.map (processXml  (_));
      case e: Elem if e.label == "num" => print("{");e.child.map (processXml  (_)); print("}");
      case e: Elem if e.label == "den" => print("{");e.child.map (processXml  (_)); print("}");
      case e: Elem if e.label == "sqrt" => print("\\sqrt{");e.child.map (processXml  (_)); print("}");
      case e: Elem if e.label == "power" => print("^{");e.child.map (processXml  (_)); print("}");
      case e: Elem =>e.child.map (processXml  (_)) 
      case e: Text =>print(e.text)

    }
  }
    
def xmlToLatex (n:Node):String =
  {
    n match
    {
      case e: Elem if e.label == "rules" => ""
      case e: Elem if e.label == "exp" => "$" + e.child.foldLeft(new String())((x,y)=> x + xmlToLatex  (y)) +"$";
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
 
    
   /* val ruleWriter = new RewriteRule {
    
    override def transform(node: Node) = 
    {
      node match
      {
        case e :Elem => e.child.map  ( transform (_)) ; // Text("$")
        
      }
    }
*/
  println (xmlToLatex(testDoc))
  
  println (xmlToLatex(testDoc2))
  
  println (xmlToLatex(testDoc3))
  
  println (xmlToLatex(testDoc4))
 
  println (xmlToLatex(testDoc5))


  
  

  }
}

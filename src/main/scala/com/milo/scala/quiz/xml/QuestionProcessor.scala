package com.milo.scala.quiz.xml

import java.io.File
import scala.io.Source
import scala.xml.XML
import scala.xml.Elem
import scala.collection.immutable.Seq
import scala.collection.immutable.Map
import scala.util.Random
import scala.xml.transform.RewriteRule
import scala.xml.Node
import scala.xml.Text
import scala.xml.transform.RuleTransformer


case class XmlTransformer(n:Node , varMap :Map[String,Int]) extends RewriteRule
{
    override def transform(n: Node): Seq[Node]= 
  {
    n match 
    {
      case e:Elem if e.label == "var" => Array[Node](  {println("transform");Text(String.valueOf(varMap.get(n.attribute("ref").get.text).get ) )}).toSeq
      case _ => n
    }
  }
    def transform (): Seq[Node]=
    {
      transform(n)
    }
}

class QuestionProcessor extends RewriteRule{
  
  
  
  
   def transform(n: Node, varMap :Map[String,Int]): Seq[Node]= 
  {
   new RuleTransformer (XmlTransformer(n,varMap))(n)
  }
  
  def processXML(f:File)
  {
    val xmlDoc = XML.loadFile(f)
    xmlDoc \\ "var" \ "@ref" 
  }
  

  
  def extractVarsValues(e:Elem):List[(String,String)]  =
  {
   val varEle = e \\ "var"
   val varEleRef = e \\ "var" \ "@ref"
   varEle.filter(x => {x.attribute("ref")!= None && x.attribute("max")!= None }).map((x)=>(( x.attribute("ref").get.text),x.attribute("max").get.text)).toList
  // varEleRef.foreach(println(_))
   //varEleRef.map((x)=>x.text).toList   
  }


  
  def assignValues(list : List[(String,String)]):Map[String,Int] =
  {
   list.foldLeft( Map[String,Int]()) ((acc, tpl)=>{acc + (tpl._1 -> Random.nextInt(tpl._2.toInt))})
    //list.map(x => {x._1 -> Random.nextInt(x._2.toInt)} )    
  }
  
  def extractVars(e:Elem):List[String]  =
  {
   val varEle = e \\ "var"
   val varEleRef = e \\ "var" \ "@ref"
   varEle.filter(x => x.attribute("ref")!= None).map(_.attribute("ref").get.text).toList
  // varEleRef.foreach(println(_))
   //varEleRef.map((x)=>x.text).toList   
  }

}
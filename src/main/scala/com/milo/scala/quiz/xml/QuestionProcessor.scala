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
import scala.xml.Attribute
import com.milo.scala.quiz.parser.RuleBuilder
import com.milo.scala.quiz.node.BooleanNode
import scala.collection.mutable.Map
import scala.collection.immutable.Map
//import com.milo.scala.quiz.node.Node


case class XmlTransformer(n:Node , varMap :scala.collection.immutable.Map[String,Double]) extends RewriteRule
{
    override def transform(n: Node): Seq[Node]= 
  {
    n match 
    {
      case e:Elem if e.label == "var" => Array[Node](  {Text(String.valueOf(varMap.get(n.attribute("ref").get.text).get ) )}).toSeq
      case _ => n
    }
  }
    def transform (): Seq[Node]=
    {
      transform(n)
    }
}

class QuestionProcessor extends RewriteRule{
  type ParserNode = com.milo.scala.quiz.node.BooleanNode
  
  implicit var map:scala.collection.mutable.Map[String,com.milo.scala.quiz.node.Node] =  scala.collection.mutable.Map[String,com.milo.scala.quiz.node.Node]()
  implicit var nodeMap:scala.collection.mutable.Map[String,BooleanNode] =  scala.collection.mutable.Map[String,BooleanNode]()
  implicit var variableMap:scala.collection.mutable.Map[String,Double] = scala.collection.mutable.Map[String,Double]()
  
  
   def transform(n: Node, varMap :scala.collection.immutable.Map[String,Double]): Seq[Node]= 
  {
   new RuleTransformer (XmlTransformer(n,varMap))(n)
  }
  
  def processXML(f:File)
  {
    val xmlDoc = XML.loadFile(f)
    xmlDoc \\ "var" \ "@ref" 
  }
  def extractRules(e:Elem):List[String] =
  {
    val varEle = e\\"rules"\"rule"
    val rules2 = varEle.filter(x => {x.attribute("exp")!= None}).map(_.attribute("exp").get.text)
        rules2.toList
    
  }

  
  def extractVarsValues(e:Elem):List[(String,String)]  =
  {
   val varEle = e \\ "var"
   val varEleRef = e \\ "var" \ "@ref"
   varEle.filter(x => {x.attribute("ref")!= None && x.attribute("max")!= None }).map((x)=>(( x.attribute("ref").get.text),x.attribute("max").get.text)).toList
  // varEleRef.foreach(println(_))
   //varEleRef.map((x)=>x.text).toList   
  }


  
  def assignValues(list : List[(String,String)]):scala.collection.immutable.Map[String,Int] =
  {
   list.foldLeft( scala.collection.immutable.Map[String,Int]()) ((acc, tpl)=>{acc + (tpl._1 -> Random.nextInt(tpl._2.toInt))})
    //list.map(x => {x._1 -> Random.nextInt(x._2.toInt)} )    
  }
  
  def assignDoubleValues(list : List[(String,String)]):scala.collection.immutable.Map[String,Double] =
  {
   list.foldLeft( scala.collection.immutable.Map[String,Double]()) ((acc, tpl)=>{acc + (tpl._1 -> Random.nextInt(Integer parseInt tpl._2))})
    //list.map(x => {x._1 -> Random.nextInt(x._2.toInt)} )    
  }
  
  
  def extractVars(e:Elem):List[String]  =
  {
   val varEle = e \\ "var"
   val varEleRef = e \\ "var" \ "@ref"
   varEle.filter(x => x.attribute("ref")!= None).map(_.attribute("ref").get.text).toList
  }
  
  def generateQuestion(e:Elem):Seq[Node] =
  {    
    while(!processQuestionRules(e))
    {
      variableMap.empty
    }    
    this.transform(e,variableMap.toMap )
  }
  
  
  
  
  def processQuestionRules(e:Elem):Boolean =
  {
    val rules = this.extractRules(e)
    variableMap = assignDoubleValues(extractVarsValues(e)).toSeq.foldLeft(scala.collection.mutable.Map[String,Double]())((m,item)=> m+=item)
    val ruleBuilder = new RuleBuilder
    rules.forall(ruleBuilder.startParseNodes(_).value)
  }

}
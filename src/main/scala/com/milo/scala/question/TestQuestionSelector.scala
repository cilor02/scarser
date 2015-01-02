package com.milo.scala.quiz.misc

import com.milo.scala.quiz.xml.QuestionProcessor
import com.milo.scala.question.RandomQuestionSelector
import scala.xml.Elem
import scala.util.Random

object TestQuestionSelector extends App
{
  
  override def main(args:Array[String]){

    val randQuestionSelector = new RandomQuestionSelector ("src/test/resources/testXml")
    
    val nodes = randQuestionSelector.selectNodes("quadratic","surds")
     val qProcessor = new QuestionProcessor
    
    println (( 1 to 20 ).map ( (_) =>Random.nextInt(nodes.size)))
    println (( 1 to 20 ).map ( (_) =>Random.nextInt(nodes.size)))
    println (( 1 to 20 ).map ( (_) =>Random.nextInt(nodes.size)))
    println (( 1 to 20 ).map ( (_) =>Random.nextInt(nodes.size)))
 
    val randNodePos = ( 1 to 10 ).map ( (_) =>Random.nextInt(nodes.size))
    
    val questioNodes = randNodePos.map( (nodes drop _ head))
    questioNodes.foreach({case e:Elem =>qProcessor.extractRules(e) case _ =>})
    
    //nodes.foreach({case e:Elem =>qProcessor.extractRules(e) case _ =>})  //SIDE EFFECTS ALERT!!!!
    val seqQuestions = questioNodes.map({case e:Elem =>qProcessor.generateQuestion(e).toSeq.head case _ =>})// .foreach(println _)
    
    val questionRoot = <questions/>
      val doc = questionRoot +: seqQuestions
      println(doc )
    
    val questionPaper = <questions>{seqQuestions}</questions>
    val latex = randQuestionSelector.xmlToLatex(questionPaper)
     println(latex )
    
    

  }
}
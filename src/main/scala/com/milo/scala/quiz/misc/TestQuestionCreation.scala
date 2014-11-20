package com.milo.scala.quiz.misc

import com.milo.scala.quiz.xml.QuestionProcessor

object TestQuestionCreation extends App
{
  
  override def main(args:Array[String]){
  
  val qProcessor = new QuestionProcessor()
  
  val testDoc = <test>
<rules>
<rule exp="a+b = 2 * c"/>
<rule exp="a+b &lt; 12"/>
</rules>
<question-text>
<var ref="a" max="10"/> x*x + <var ref="b" max="20"/>x + <var ref="c" max="10"/> = 0
</question-text>
</test>;
  
  
val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head

println(substitutedSequences)
  }
}
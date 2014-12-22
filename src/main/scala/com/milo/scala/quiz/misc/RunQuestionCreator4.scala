package com.milo.scala.quiz.misc

import com.milo.scala.quiz.xml.QuestionProcessor

object RunQuestionCreator4 extends App
{
  override def main(args:Array[String]){
  
  val qProcessor = new QuestionProcessor()
  
  val testDoc = <test>
<rules>
  <rule exp="b*b &gt; 4 * a * c"/>
</rules>
<question-text>


<derived-var ref="solution1" value="((b * b - 4 * a * c)**(1/2) - b)/(2 * a)"/>
<derived-var ref="solution2" value="((0 - 1)* b -(b * b - 4 * a * c)**(1/2) )/(2 * a)"/>
<derived-var ref="solution3" value="((b * b - 4 * a * c)**(0.5) - b)/(2 * a)"/>
<derived-var ref="solution4" value="(0 - b -(b * b - 4 * a * c)**(0.5) )/(2 * a)"/>


</question-text>
 
</test>;
  
  
val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head

println(substitutedSequences)
  }
}

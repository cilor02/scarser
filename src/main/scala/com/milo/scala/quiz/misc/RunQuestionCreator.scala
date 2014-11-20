package com.milo.scala.quiz.misc

import com.milo.scala.quiz.xml.QuestionProcessor

object RunQuestionCreator extends App
{
  override def main(args:Array[String]){
  
  val qProcessor = new QuestionProcessor()
  
  val testDoc = <test>
<rules>
  <rule exp="b*b &gt; 4 * a * c"/>
</rules>
<question-text>

Solve <var ref="a" max="10"/>x * x + <var ref="b" max="20"/>x + <var ref="c" max="10"/> = 0 
 <derived-var ref="sqrooted" value="b * b - 4 * a * c"/>
<derived-var ref="numerator1" value="((0 - 1) * b) - sqrooted ** (1/2)"/>
<derived-var ref="solution1" value="(numerator1 / 2) / a "/>
 
<derived-var ref="numerator2" value="((0 - 1) * b) + sqrooted ** (1/2)"/>
<derived-var ref="solution2" value="(numerator2 / 2 )/ a "/>




</question-text>
 
</test>;
  
  
val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head

println(substitutedSequences)
  }
}

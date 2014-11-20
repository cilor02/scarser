package com.milo.scala.quiz.misc

import com.milo.scala.quiz.xml.QuestionProcessor

object RunQuestionCreator2 extends App
{
  override def main(args:Array[String]){
  
  val qProcessor = new QuestionProcessor()
  
  val testDoc = <test>
<rules>
   <rule value="factor &gt; 0"/>
</rules>
<question-text>

root 1 <var ref="root1" max="10"/>
root 2 <var ref="root2" max="10"/>
root 3 <var ref="factor" max="7"/>

Solve <derived-var ref="coeff1" value="factor"/>x * x + <derived-var ref="coeff2" value="factor * (root1 + root2)"/>x + <derived-var ref="coeff3" value="root1 * root2 * factor"/> = 0 

</question-text>
 
</test>;
  
  
val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head

println(substitutedSequences)
  }
}

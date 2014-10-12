package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.node.LeafNumericNode
import scala.xml.XML
import com.milo.scala.quiz.xml.QuestionProcessor

class TestQuestionProcessor extends FunSpec with ShouldMatchers {




//rNode.value

  describe("single var element") {
    it("should give 1") {
      val qProcessor = new QuestionProcessor
      
      val testDoc = <test><var ref="a"/></test>;
      assert(qProcessor.extractVars(testDoc).size == 1)
    }
  }
  
  
  describe("multi var element") {
    it("should give 4") {
      val qProcessor = new QuestionProcessor
      
      val testDoc = <test>
                       <var ref="a"/>
                       <fraction>
                        <numerator>
                          <var ref="b"/> 
                        </numerator>
                       <denominator>
                        <var ref="c"/>
                       </denominator>
                      </fraction>
                     <var ref="d"/>
                   </test>;
      
          
      val testDoc2 = <test>
                       <var ref="a" max="5"/>
                       <fraction>
                        <numerator>
                          <var ref="b" max="7"/> 
                        </numerator>
                       <denominator>
                        <var ref="c" max="8"/>
                       </denominator>
                      </fraction>
                     <var ref="d" max="2"/>
                   </test>;
      
      val variables = qProcessor.assignValues(qProcessor.extractVarsValues(testDoc2))
      println("variables"+variables)
      println("vars "+qProcessor.extractVarsValues(testDoc2))
      
      println("substituted " + qProcessor.transform(testDoc2, variables))
      println("testDoc" +  qProcessor.assignValues(qProcessor.extractVarsValues(testDoc2)))
      assert(qProcessor.extractVars(testDoc).size == 4)
    }
  }
  
  
}

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
import scala.xml.Document
import scala.xml.Elem
import java.lang.Double

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
      
      val variables = qProcessor.assignDoubleValues(qProcessor.extractVarsValues(testDoc2))

      assert(qProcessor.extractVars(testDoc).size == 4)
    }
  }
  
  
  describe("multi var element substitution 1 level") {
    it("should give 4") {
      val qProcessor = new QuestionProcessor
      

      
          
      val testDoc2 = <test>
                       <a><var ref="a" max="5"/></a>
                       <b><var ref="b" max="7"/></b> 
                       <c><var ref="c" max="8"/></c>
                       <d><var ref="d" max="2"/></d>
                   </test>;
      println(" q processor " + qProcessor.extractVarsValues(testDoc2))
      
      val variables = qProcessor.assignDoubleValues(qProcessor.extractVarsValues(testDoc2))
      //println("variables"+variables)
      //println("vars "+qProcessor.extractVarsValues(testDoc2))
      
      println("substituted " + qProcessor.transform(testDoc2, variables))
      
      
      //println("testDoc" +  qProcessor.assignValues(qProcessor.extractVarsValues(testDoc2)))
      
      val xformedXml = qProcessor.transform(testDoc2, variables)
      
      val root = xformedXml.toSeq.head
      /*root.child.foreach 
      {
        case <a>{n}</a> => println("<a>"+(Integer parseInt n.text))
        case <b>{n}</b> => println("<b>"+(Integer parseInt n.text))
        case <c>{n}</c> => println("<c>"+(Integer parseInt n.text))
        case <d>{n}</d> => println("<d>"+(Integer parseInt n.text))
      }*/

      root.child.filter({case e:Elem => true case _ => false}).map((n)=>(n.label,  Double parseDouble n.text)).forall(      {
        case ("a",b) => b < 5
        case ("b",b) => b< 7
        case ("c",b) => b < 8
        case ("d",b) => b < 2
        
      }) 

     root.child.filter({case e:Elem => true case _ => false}).map((n)=>(n.label, Double parseDouble n.text)).foreach(      {
        case ("a",b) => assert(b < 5)
        case ("b",b) => assert(b < 7)
        case ("c",b) => assert(b < 8)
        case ("d",b) => assert(b < 2)
        
      }) 
      
      assert(qProcessor.extractVars(testDoc2).size == 4)
    }
  }
  
  
    
  describe("extract rules") {
    it("should give 4") {
      val qProcessor = new QuestionProcessor
      
      val testDoc = <test>
<rules>
<rule exp="a+b > c"/>
<rule exp="a=d"/>
</rules>

                   </test>;
val rules = qProcessor.extractRules(testDoc)
assert(rules.size == 2)

assert(rules.contains("a=d"))
assert(rules.contains("a+b > c"))

      
    }
  }
  
  
      
  describe("whole question") {
    it("should give 4") {
      val qProcessor = new QuestionProcessor
      
      val testDoc = <test>
<rules>
<rule exp="a+b > c"/>
<rule exp="a=d"/>
</rules>

 <a><var ref="a" max="10"/></a>
 <b><var ref="b" max="10"/></b> 
 <c><var ref="c" max="10"/></c>
 <d><var ref="d" max="10"/></d>
</test>;

val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head
println(substitutedSequences)
val aText =  Double parseDouble (substitutedSequences.child.filter({case e:Elem if e.label == "a" => true case _ =>false}).head.text )

val bText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "b" => true case _ =>false}).head.text

val cText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "c" => true case _ =>false}).head.text

val dText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "d" => true case _ =>false}).head.text

assert(aText == dText)

assert (aText + bText > cText)
     
    }
  }
  
  
  
    describe("more questions") {
    it("all conditions should be true") {
      val qProcessor = new QuestionProcessor
      
      val testDoc = <test>
<rules>
<rule exp="a+b =2 * c"/>
<rule exp="4 * a=d"/>
</rules>

 <a><var ref="a" max="10"/></a>
 <b><var ref="b" max="10"/></b> 
 <c><var ref="c" max="10"/></c>
 <d><var ref="d" max="10"/></d>
</test>;

val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head

println(substitutedSequences)

val aText =  Double parseDouble (substitutedSequences.child.filter({case e:Elem if e.label == "a" => true case _ =>false}).head.text )

val bText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "b" => true case _ =>false}).head.text

val cText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "c" => true case _ =>false}).head.text

val dText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "d" => true case _ =>false}).head.text

assert( 4 * aText == dText)

assert (aText + bText == 2 * cText)
     
    }
  }
  
    
        describe("more questions with derived variables") {
    it("all conditions should be true") {
      val qProcessor = new QuestionProcessor
      
      val testDoc = <test>
<rules>
<rule exp="a+b =2 * c"/>
<rule exp="4 * a=d"/>
</rules>

 <a><var ref="a" max="10"/></a>
 <b><var ref="b" max="10"/></b> 
 <c><var ref="c" max="10"/></c>
 <d><var ref="d" max="10"/></d>
 <e><derived-var ref="e" value="a+c"/></e>
 <f><derived-var ref="f" value="b+d"/></f>
</test>;

val rules = qProcessor.extractRules(testDoc)

val substitutedSequences = qProcessor.generateQuestion(testDoc).toSeq.head

println(substitutedSequences)

val aText =  Double parseDouble (substitutedSequences.child.filter({case e:Elem if e.label == "a" => true case _ =>false}).head.text )

val bText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "b" => true case _ =>false}).head.text

val cText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "c" => true case _ =>false}).head.text

val dText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "d" => true case _ =>false}).head.text

val eText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "e" => true case _ =>false}).head.text

val fText =  Double parseDouble substitutedSequences.child.filter({case e:Elem if e.label == "f" => true case _ =>false}).head.text


assert( 4 * aText == dText)

assert (aText + bText == 2 * cText)

assert (eText ==  aText + cText)     

assert (fText ==  bText + dText)
    }
  }
  
  
}

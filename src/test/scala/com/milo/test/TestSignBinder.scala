package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.parser.state.SignBinder

class TestSignBinder extends FunSpec with ShouldMatchers {

val symbols = new SignBinder(List[String] ("6","+","7"))


//rNode.value

  describe("bind 6,+,7") {
    it("should give 6,+7") {
      assert(symbols.bindSigns == List[String] ("6","+","7"))
    }
  }

 describe("bind 6,+,-,7") {
    it("should give 6,+,-7") {
      assert(new SignBinder(List[String] ("6","+","-","7")).bindSigns == List[String] ("6","+","-7"))
    }
  }
  
   describe("bind 6,-,-,7") {
    it("should give 6,-,-7") {
      assert(new SignBinder(List[String] ("6","-","-","7")).bindSigns == List[String] ("6","+","-1","*","-7"))
    }
  }
   
      describe("bind 6,**,-,7") {
    it("should give 6,**,-7") {
      assert(new SignBinder(List[String] ("6","**","-","7")).bindSigns == List[String] ("6","**","-7"))
    }
  }
}

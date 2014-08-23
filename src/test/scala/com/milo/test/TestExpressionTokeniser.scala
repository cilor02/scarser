package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser

class TestExpressionTokeniser extends FunSpec with ShouldMatchers {
val tokens = ExpressionTokeniser("6+7")
tokens.startTokenising
println(tokens.tokens)

//rNode.value
  describe("parse a") {
    it("should give List(a)") {
      assert(ExpressionTokeniser("a").startTokenising  == List("a"))
    }
  }


  describe("parse 6+7") {
    it("should give List('6','+','7')") {
      assert(tokens.tokens  == List("6","+","7"))
    }
  }
  
val tokens2 = ExpressionTokeniser("6 + 7")
tokens2.startTokenising

  describe("parse 6 + 7") {
    it("should give List('6','+','7')") {
      assert(tokens2.tokens  == List("6","+","7"))
    }
  }
}

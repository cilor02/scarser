package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.node.TrueNode
import com.milo.scala.quiz.node.FalseNode

class TestTrueOrFalseNode extends FunSpec with ShouldMatchers {

//rNode.value

  describe("parse True Node") {
    it("should give true") {
      assert(TrueNode().value)
    }
  }


  describe("parse False Node") {
    it("should give false") {
      assert(FalseNode().value)
    }
  }

  
}

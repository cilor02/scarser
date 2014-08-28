package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map

class TestBinaryNode extends FunSpec with ShouldMatchers {
implicit var map:Map[String,Node] =  Map[String,Node]()
implicit var variableMap:Map[String,Double] = Map[String,Double]()
val node = new BinaryNode("+","6","7")


//rNode.value

  describe("parse 6+7") {
    it("should give 13") {
      assert(node.value  == 13)
    }
  }


  describe("parse 7 + -6") {
    it("should give 1") {
      assert(new BinaryNode("+","-6","7").value  == 1)
    }
  }
  
}

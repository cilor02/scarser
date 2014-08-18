package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.node.LeafNumericNode

class TestVariableNode extends FunSpec with ShouldMatchers {
implicit var map:Map[String,Node] =  Map[String,Node]()
implicit var variableMap:Map[String,Double] = Map[String,Double]()

variableMap += ("a" -> 17)
variableMap += ("b" -> 10)

val node = new BinaryNode("+","a","b")


//rNode.value

  describe("parse b+17 where b = 10") {
    it("should give 27") {
      assert(node.value  == 27)
    }
  }
  
}

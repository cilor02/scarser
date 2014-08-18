package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.node.LeafNumericNode

class TestVarNode extends FunSpec with ShouldMatchers {
implicit var map:Map[String,Node] =  Map[String,Node]()
map += ("com.milo.var1" -> new LeafNumericNode(16))
implicit var variableMap:Map[String,Double] = Map[String,Double]()
val node = new BinaryNode("+","17","com.milo.var1")


//rNode.value

  describe("parse 16+17") {
    it("should give 33") {
      assert(node.value  == 23)
    }
  }
  
}

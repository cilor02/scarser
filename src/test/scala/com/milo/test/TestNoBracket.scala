package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.parser.NoBracketExpressionBuilder
import com.milo.scala.quiz.node.LeafVarNode

class TestNoBracket extends FunSpec with ShouldMatchers {
implicit var map:Map[String,Node] =  Map[String,Node]()
implicit var variableMap:Map[String,Double] = Map[String,Double]()
var tokeniser = new ExpressionTokeniser("6 + 6 * 12 / 9 - 4")
tokeniser.startTokenising 
val node = new NoBracketExpressionBuilder(tokeniser.tokens.toList)
node.process

//rNode.value

  describe("parse 6+7") {
    it("should give 10") {
      assert(new LeafVarNode(node.newList.head).value  == 10)
    }
  }
  
}

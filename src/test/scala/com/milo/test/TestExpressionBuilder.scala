package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.parser.NoBracketExpressionBuilder
import com.milo.scala.quiz.node.LeafVarNode
import com.milo.scala.quiz.parser.ExpressionBuilder

class TestExpressionBuilder extends FunSpec with ShouldMatchers {
implicit var map:Map[String,Node] =  Map[String,Node]()
implicit var variableMap:Map[String,Double] = Map[String,Double]()

//var tokeniser = new ExpressionTokeniser("(6 + 6) * 12 / 9 - 4")
//tokeniser.startTokenising 
//val node = new ExpressionBuilder(tokeniser.tokens.toList)
//val tokens = node.process
//println("================="+ tokens)
//rNode.value

 // describe("(6 + 6) * 12 / 9 - 4") {
 //   it("should give 10") {
 //     assert(new LeafVarNode(tokens.head).value  == 12)
 //   }
 // }


var tokeniser1 = new ExpressionTokeniser("(6 + 0) * 12 + 8 / (9 - 4) + 1")
tokeniser1.startTokenising 
val node1 = new ExpressionBuilder(tokeniser1.tokens.toList)
val tokens1 = node1.processNodes

  describe("(6 + 0) * 12 + 8 / (9 - 4) + 1") {
    it("should give 16") {
      assert(new LeafVarNode(tokens1.head).value  == 74.6)
    }
  }


var tokeniser2 = new ExpressionTokeniser("((6 + 0) * 12 + 8) / (9 - 4) + 1")
tokeniser2.startTokenising 
val node2 = new ExpressionBuilder(tokeniser2.tokens.toList)
val tokens2 = node2.processNodes

  describe("((6 + 0) * 12 + 8) / (9 - 4) + 1") {
    it("should give 17") {
      assert(new LeafVarNode(tokens2.head).value  == 17)
    }
  }

var tokeniser3 = new ExpressionTokeniser("(6 + 0) * (12 + 8) / (9 - 4) + 1")
tokeniser3.startTokenising 
val node3 = new ExpressionBuilder(tokeniser3.tokens.toList)
val tokens3 = node3.processNodes

  describe("(6 + 0) * (12 + 8) / (9 - 4) + 1") {
    it("should give 25") {
      assert(new LeafVarNode(tokens3.head).value  == 25)
    }
  }


var tokeniser4 = new ExpressionTokeniser("(6 + 0) * (12 + 8) / (9 - 4 + 1)")
tokeniser4.startTokenising 
val node4 = new ExpressionBuilder(tokeniser4.tokens.toList)
val tokens4 = node4.processNodes

  describe("(6 + 0) * (12 + 8) / (9 - 4 + 1)") {
    it("should give 20") {
      assert(new LeafVarNode(tokens4.head).value  == 20)
    }
  }


var tokeniser5 = new ExpressionTokeniser("(6 + 0) * (12 + 8) / (9 - (4 + 1))")
tokeniser5.startTokenising 
val node5 = new ExpressionBuilder(tokeniser5.tokens.toList)
val tokens5 = node5.processNodes

  describe("(6 + 0) * (12 + 8) / (9 - (4 + 1))") {
    it("should give 30") {
      assert(new LeafVarNode(tokens5.head).value  == 30)
    }
  }

}

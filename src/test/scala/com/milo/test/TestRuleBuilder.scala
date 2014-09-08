package com.milo.test
import org.scalatest._
import com.milo.scala.quiz.parser.ExpressionTokeniser
import com.milo.scala.quiz.node.BinaryNode
import com.milo.scala.quiz.node.BooleanNode
import com.milo.scala.quiz.node.Node
import scala.collection.mutable.Map
import com.milo.scala.quiz.parser.NoBracketExpressionBuilder
import com.milo.scala.quiz.node.LeafVarNode
import com.milo.scala.quiz.parser.ExpressionBuilder
import com.milo.scala.parser.state.SignBinder
import com.milo.scala.quiz.node.RelationalNode
import com.milo.scala.quiz.node.BinaryPairNode
import com.milo.scala.quiz.parser.RuleBuilder

class TestRuleBuilder extends FunSpec with ShouldMatchers {
implicit var nodeMap:Map[String,BooleanNode] =  Map[String,BooleanNode]()
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


  describe("(6 + 12 > 9)") {
    it("should give true") {
      assert(new RuleBuilder().startParseNodes("(6 + 12 > 9)").value)
    }
  }

  describe("(6 + 12 > 9) and (1 < 4 - 2)") {
    it("should give true") {
      assert(new RuleBuilder().startParseNodes("(6 + 12 > 9) and (1 < 4 - 2)").value)
    }
  }

  
  
  describe("6 + 12 > 9 and 1 < 4 - 2") {
    it("should give true") {
      assert(new RuleBuilder().startParseNodes("6 + 12 > 9 and 1 < 4 - 2").value)
    }
  }
  
}

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
import com.milo.scala.parser.state.SignBinder

class TestVariableExpressionBuilder extends FunSpec with ShouldMatchers {
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

variableMap.+=("a"->6)
variableMap.+=("b"->7)
variableMap.+=("c"->8)
variableMap.+=("d"->9)

var tokeniser1 = new ExpressionTokeniser("(a + 0) * b + 8 / (d - 4) + c")
tokeniser1.startTokenising 
val node1 = new ExpressionBuilder(tokeniser1.tokens.toList)
val tokens1 = node1.processNodes

  describe("(a + 0) * b + 8 / (d - 4) + c") {
    it("should give 51.6") {
      assert(new LeafVarNode(tokens1.head).value  == 51.6)
    }
  }

}

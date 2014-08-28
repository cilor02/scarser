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
import com.milo.scala.quiz.node.RelationalNode

class TestRelationalNode extends FunSpec with ShouldMatchers {
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


  describe("6 + 12 = 12 + 6") {
    it("should give true") {
      assert(new RelationalNode("=","6 + 12","12 + 6").value)
    }
  }


  describe("6 + 13 > 12 + 6") {
    it("should give true") {
      assert(new RelationalNode(">","6 + 13","12 + 6").value)
    }
  }

    describe("13 = 13 ") {
    it("should give true") {
      assert(new RelationalNode("=","13","13").value)
    }
  }

        describe("13 < 14 ") {
    it("should give true") {
      assert(new RelationalNode("<","13","14").value)
    }
  }
        
        
  variableMap += ("a"-> 6, "b"->7,"c"->8)    
  
    describe("a=6,b=7,c=8 : a = 6") {
    it("should give true") {
      assert(new RelationalNode("=","a","6").value)
    }
  }  
  
    describe("a=6,b=7,c=8 : a + 1 = 7") {
    it("should give true") {
      assert(new RelationalNode("=","a + 1","7").value)
    }
  }  
  
    describe("a=6,b=7,c=8 : a + 1 = b") {
    it("should give true") {
      assert(new RelationalNode("=","a + 1","b").value)
    }
  } 
  
    
    describe("a=6,b=7,c=8 : a + 1 = b + 0") {
    it("should give true") {
      assert(new RelationalNode("=","a + 1","b + 0").value)
    }
  }
  
    describe("a=6,b=7,c=8 : (a + 1) * b = a * c + 1") {
    it("should give true") {
      assert(new RelationalNode("=","(a + 1) * b","a * c + 1").value)
    }
  }    
      
    
     describe("a=6,b=7,c=8 : (a + 1) * b != a * c") {
    it("should give true") {
      assert(new RelationalNode("!=","(a + 1) * b","a * c").value)
    }
  } 
    
}

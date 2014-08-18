package com.milo.scala.quiz.parser


import scala.collection.mutable.Map
import com.milo.scala.quiz.node.BinaryPairNode
import com.milo.scala.quiz.node.BooleanNode
import com.milo.scala.quiz.node.BinaryPairNode
import com.milo.scala.quiz.node.Node

class RuleBuilder (implicit var map:Map[String,Node], implicit var variableMap:Map[String, Double], var boolMap :Map[String,List[String]])
{
  
  //val boolMap:Map[String, List[String]] = Map[String, List[String]]()
  val nodeMap:Map[String, BooleanNode] = Map[String, BooleanNode]()
  
  var boolOps = List("or","and")
  val arithOps = List("<=",">=","=",">","<", "factorOf", "multipleOf")
  val prefix = "com.milo.BooleanPhrase"
  var nameCount = 0
  
  def nextName():String =
  {
    nameCount+=1
    prefix.+(nameCount)
  }
  
  def parse (s:String):List[String] =
  {
    var workString = spaceOutBooleanOperators(s)
    for (op <- boolOps)
    {
     var startingFrom = 0
     while (startingFrom > -1)
     {
     {
      var opIndex = workString.indexOf( op, startingFrom)
      val bracketrange = findEnclosingBrackets(workString,op,startingFrom)
      val bracket1 = bracketrange._1
      val bracket2 = bracketrange._2
      
      if(bracketrange._1 > -1  && bracketrange._2 > -1)
      {        
        val name = nextName
        val subPhrase = workString.substring(bracket1 + 1, bracket2)
        
        println(name + " : " +tokenise(subPhrase,boolOps))
        boolMap.+=(name -> parse(subPhrase))
        workString = workString.take(bracket1 ) + name + workString.drop(bracket2 + 1)        
      }
      startingFrom = workString.indexOf( op, opIndex + op.length());     
    }
    }
   }
    println("no bracket phrase : "+ workString)
    tokenise(workString,boolOps)
  }
  

  
  def findEnclosingBrackets(s:String,op:String,from:Int):Tuple2[Int,Int] =
  {
    (findOddLeftBracket(s, op,from),findOddRightBracket(s, op,from))
  }
  
  def findOddRightBracket(s:String,op:String,from:Int):Int =
    {
      var posOp = s.indexOf(op,from)
      var oddRightBracketCount = 0
      
      while(oddRightBracketCount<1 && posOp < s.length() - 1)
      {
        posOp += 1
        s.charAt(posOp) match 
        {
        
          case ')' => oddRightBracketCount.+=(1)
          case '(' => oddRightBracketCount.-=(1)

          case _ => 
          
        }
      }
      if(oddRightBracketCount !=1)-1 else posOp
      
    }
  
    def findOddLeftBracket(s:String,op:String,from:Int):Int =
    {
      var posOp = s.indexOf(op,from)
      var oddLeftBracketCount = 0
      
      while(oddLeftBracketCount<1 && posOp >0)   
      {
        posOp -= 1
        s.charAt(posOp) match 
        {
        
          case ')' => oddLeftBracketCount.-=(1)
          case '(' => oddLeftBracketCount.+=(1)

          case _ => 
          
        }
      }    

      if(oddLeftBracketCount !=1)-1 else posOp
      
    }
  
  
    
def spaceOutBooleanOperators (s:String):String =
{
      
       // val s:String = new String("ftc and(hxjc)or kck or(gvk)and(vvhl)and ablbl org) andblglg or andlglbglb ")
 // println(tokens)
 def replaceOp (op:String):String =
 {
    op.replaceAllLiterally(")", ") ").replaceAllLiterally("(", " (")   
 }
  val leftSide  = List( ' ', ')')
  val rightSide = List( ' ', '(')
  val ops = List("or","and")
  var nwStr = s
  for( 
      l <- leftSide;
      r <- rightSide;
      op <- ops
  )
  {
    val inSituOp = ((new String()+l)+op)+r
        
    nwStr = nwStr.replaceAllLiterally(inSituOp, replaceOp(inSituOp))
  }
   
 
      nwStr
    }
    
    
    def tokenise(s:String, ops:List[String]):List[String]
    =
    {
      if (ops.isEmpty)
        return List(s)
     val posOp = s.indexOf(ops.head)
     if(posOp > -1 && s(posOp -1) == ' ' && s(posOp + ops.head.length()) == ' ')
     {
       return tokenise(s.take(posOp).mkString,ops.tail):::List(ops.head):::tokenise(s.drop(posOp + ops.head.length()).mkString,ops)
     }
     tokenise(s,ops.tail)
    }
    
    def processBooleanStatement (s:String, ops:List[String]):List[String] =
    {
      if (ops.isEmpty)
      {
        return Nil
      }
      val leftExpAndRightExp = s.split(ops.head)
      if(leftExpAndRightExp.length < 1)
        processBooleanStatement(s, ops.tail)
      else
        leftExpAndRightExp.toList ::: List( ops.head)
     }
    
    def buildNodes(tokens:List[String], ops:List[String]) 
    {
      if(!ops.isEmpty)
      {
      val idx = tokens.indexWhere(_== ops.head)

      
      if(idx > -1)
      {
        val leftBoolToken  = tokens(idx - 1)
        val rightBoolToken = tokens(idx + 1)
 
        val newNode = new BinaryPairNode (tokens(idx),tokens(idx - 1), tokens(idx + 1))
        
        val leftArithmeticExp  = processBooleanStatement(leftBoolToken, arithOps)
        val rightArithmeticExp = processBooleanStatement(rightBoolToken, arithOps)
        println(leftArithmeticExp)
        println(rightArithmeticExp)
        val tk1 = new ExpressionTokeniser(leftArithmeticExp.head)
        val tk2 = new ExpressionTokeniser(leftArithmeticExp.tail.head)
        val b1 = new ExpressionBuilder(tk1.startTokenising.toList)
        val b2 = new ExpressionBuilder(tk2.startTokenising.toList)
        b1.process
        b2.process
        
        
        
        // check whether token in node map; if not then assume to be evaluated
        val newNodeName = this.nextName
        this.nodeMap+=(newNodeName -> newNode)

        val splitList = tokens.splitAt(idx -1)
        
        val newTokenList:List[String] = splitList._1:::newNodeName::(splitList._2 splitAt 3)._2
        
        buildNodes(newTokenList,ops.tail)

      
      }
      }
    }
    
}
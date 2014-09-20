package com.milo.scala.quiz.parser


import scala.collection.mutable.Map
import com.milo.scala.quiz.node.BinaryPairNode
import com.milo.scala.quiz.node.BooleanNode
import com.milo.scala.quiz.node.Node
import com.milo.scala.quiz.node.BooleanRefNode
import com.milo.scala.quiz.node.BooleanRefNode
import scala.collection.mutable.ListBuffer
import com.milo.scala.quiz.node.RelationalNode

class RuleBuilder (implicit var map:Map[String,Node],  var variableMap:Map[String, Double],nodeMap:Map[String, BooleanNode])
{
  
    
 val boolOps = List("or","and")
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
        
        parse(subPhrase)
        workString = workString.take(bracket1 ) + name + workString.drop(bracket2 + 1)
        //opIndex = workString.indexOf( op, startingFrom)
        startingFrom = 0
      }
      else
      startingFrom = workString.indexOf( op, opIndex + op.length());     
    }
    }
   }
    println("no bracket phrase : "+ workString)
    tokenise(workString,boolOps)
  }
  
  def startParseNodes (s:String):BooleanNode =
  {
    val name = nextName
    val root = parseNodes(s)
    nodeMap.+=(name -> root )
    root
  }
  
def parseNodes (s:String):BooleanNode =
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
        
        //println(name + " : " +tokenise(subPhrase,boolOps))
        nodeMap.+=(name -> parseNodes(subPhrase))
        workString = workString.take(bracket1 ) + name + workString.drop(bracket2 + 1)        
      }
      //else
      //{
        // no brackets
      //  buildNodes(tokenise(workString,boolOps),arithOps)
      //}
      startingFrom = workString.indexOf( op, opIndex + op.length());     
    }
    }
   }
    //println("no bracket phrase : "+ workString)
    //println(tokenise(workString,boolOps))
    buildNodes(tokenise(workString,boolOps),boolOps)
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
        return List(s.trim)
     val posOp = s.indexOf(ops.head)
     // println("before")
     // println("==============>>>>>" + s + ops.head + posOp + " " + (( posOp > -1 && s.charAt(posOp - 1) == ' ') ))
     // println( posOp + ops.head.length )
     // println("char at " + s.charAt(posOp + ops.head.length()).equals(' '))
     // println("XXXXXXXXXXXXXX" + posOp + ops.head.length + s.charAt(posOp + ops.head.length()).equals(' '))
     // println("after")
     if((posOp > -1 && s.charAt(posOp - 1) == ' ') && s.charAt(posOp + ops.head.length()).equals(' '))
     {     
       val charArray = s.toCharArray()
       
       var buffer = ListBuffer[Char]() 
       for(c <- charArray)
       {
         buffer += c
       }
        val sList = buffer.toList
       return tokenise(sList.take(posOp).mkString,ops.tail):::List(ops.head):::tokenise(sList.drop(posOp + ops.head.length()).mkString,ops)
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
    
    def buildNodes(tokens:List[String], ops:List[String]) :BooleanNode =
    {
      
            
      def stripOuterBrackets(s:String,p:Int):Boolean =
      {
        println( "token " + s + " bracket count " + p)
        if (s.length == 0)
          false
        else
         s.charAt(0) match 
        {
          case ')' if s.length() > 1 && p == 1  => false
          case ')' if s.length() == 1 && p > 0  => true
          case '(' => stripOuterBrackets(s.substring(1), p + 1) 
          case ')' => stripOuterBrackets(s.substring(1), p - 1) 
          
          case _ => stripOuterBrackets(s.substring(1), p )

        }
      }
      
      
      if(!ops.isEmpty)
      {
      val idx = tokens.indexWhere(_== ops.head)

      
      if(idx > -1)
      {
        val leftBoolToken  = tokens(idx - 1)
        val rightBoolToken = tokens(idx + 1)
 
        val newNode = new BinaryPairNode (tokens(idx),tokens(idx - 1), tokens(idx + 1))
     
        val newNodeName = this.nextName
        this.nodeMap+=(newNodeName -> newNode)

        val splitList = tokens.splitAt(idx -1)
        
        val newTokenList:List[String] = splitList._1:::newNodeName::(splitList._2 splitAt 3)._2
        
        buildNodes(newTokenList,ops)      
      }
      else{
          buildNodes(tokens,ops.tail)
      }
      }else
        if(nodeMap.contains(tokens.head))
      {
        new BooleanRefNode(tokens.head)
      } 
        else
        {
          if (stripOuterBrackets(tokens.head, 0))
            buildSingleBooleanTokenNodes (tokens.head.substring(1, tokens.head.length() - 1),arithOps)
          else
          {
            buildSingleBooleanTokenNodes(tokens.head,arithOps)
          }
        }
    }    
    
  def buildSingleBooleanTokenNodes(s:String, ops:List[String]):BooleanNode=
    {
    // possibly a ref to a composite sub phrase
      if (ops.isEmpty)
      {
         return nodeMap.get{s} match{
          case Some(n) => n
          //case None => Nil
      }
       
      }
      
      val leftExpAndRightExp = s.split(ops.head)
      println(leftExpAndRightExp(0) + ops.head)
      if(leftExpAndRightExp.length < 2)
        buildSingleBooleanTokenNodes(s, ops.tail)
      else
        new RelationalNode(ops.head,leftExpAndRightExp(0),leftExpAndRightExp(1))
     }
}
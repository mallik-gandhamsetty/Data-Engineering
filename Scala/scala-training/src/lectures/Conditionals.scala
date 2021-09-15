package lectures

object Conditionals extends App{

  // if(boolean_expression) {
  //   any number of expressions  // boolean expr is true
  // } else {
  //   any nuber of expression // boolen expr is false
  // }

  // if statements
  if(true) println("true") else println("false")  // single line

  val a = 50
  val b = 40
  val c = 60

  // optional else clause
  if(a == b) {
    println("a and b are equal")
  }

  // if else
  if(a == b) {
    println("a and b are equal")
  } else {
    println("a and b are not equal")
  }

  // nested if-else-if
  if(a > b) {
    println("a > b")
  } else if(a > c) {
    println("a is the greatest")
  } else if(b > c) {
    println("a is the greatest")
  } else if(b > a) {
    println("a is the greatest")
  } else {
    println("c is greatest")
  }

  // nested if-else
  if(a > b) {
    println("a > b")
  } else {
    if (a > c) {
      println("a is the greatest")
    } else if (b > a) {
      println("a is the greatest")
    } else {
      println("c is greatest")
    }
  }

  // if expresions
  val d = if(a > b) {  // a = 10 and b = 40
    println("inside if")
    a*b    // 50 * 40
    1
  } else {
    a-b   // -30
    2 // doesnt execute
  }
  println(d)   //2000

  // other languages (if as statement)
  var e = 0
  if(a > b) {  // a = 10 and b = 40
    e = a * b    // assignments do not return value
  } else {
    e = a - b
  }
  println(d)   //2000


  // If you write an If Else block to return a value then u are using it as an expression
  // if your if-else is not returning any value then u are using if as a statement
  
}

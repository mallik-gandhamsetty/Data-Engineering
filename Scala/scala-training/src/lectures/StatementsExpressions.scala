package lectures

object StatementsExpressions extends App{

  //expressions
  // val x = (anything legal here must be an expression)

  val x = 1 + 2 // 1 + 2 is an expression. val x = 1 + 2 is a statement
  println(x)


  // statements
  var aVariable = 2
  aVariable += 3               // also works with -= *= /= ..... side effects
  println(aVariable)

  // Rule of thumb:
  //      If you can't put it on the right-hand side of an assignment, it's probably a statement


  // Expressions can be defined in the form of a code block
  // last line of code in the code block decides the return value and type of the bock
  //
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    z                 // z is the return value of the block
  }

  // Quiz: Difference between "hello world" vs println("hello world")?
  // Ans: "hello world" is an expression that can be evaluated to a String and can be assigned to a variable.
  //      println("hello world") is a statement that prints to the console. It doesn't evaluate to any value.

}

package lectures

object StatementsExpressions extends App{

  //expressions
  // val x = (anything legal here must be an expression)

  val x = 1 + 2 // 1 + 2 is an expression. val x = 1 + 2 is a statement
  println(x)

  println(2 + 3 * 4)

  println(1 == x)   // == != > >= < <=


  println(!(1 == x))  // !(1 == x) is an expression

  // statements
  var aVariable = 2
  aVariable += 3               // also works with -= *= /= ..... side effects
  println(aVariable)

  // Rule of thumb:
  //      If you can't put it on the right-hand side of an assignment, it's probably a statement


  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // Quiz: Difference between "hello world" vs println("hello world")?



}

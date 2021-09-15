package practice

object Operators extends App{

  // Arithmetic Operators

  println(2 + 1) // Addition(+) operator adds two operands. For example, x+y

  println(10 - 5) // Subtraction(-) operator subtracts two operands. For example, x-y.

  println(2 * 5) // Multiplication(*) operator multiplies two operands. For example, x*y.

  println(3/2) // Division(/) operator divides the first operand by the second. For example, x/y. If both numerator and denominator are Ints. Floor Division occurs

  println(7/4)  // 1.75 truncated to 1

  println(3/2.0) // Floating point division

  println(7.0/4)  // Either numerator or denominator has to be a double to get the output with decimal point

  println(7 % 4)  // 4 goes into 7 once, with a remainder of 3. The % operator returns the remainder after division.

  // Exponent to return exponential(power) of the operands
  println(scala.math.pow(2, 3))  // 2 raised to the power of 3

  println(scala.math.pow(4, 0.5)) // square root of 4 or 2 raised to the power of 1/2

  println(2 + 10 * 10 + 3)

  println((2 + 10) * (10 + 3))

  // Variables and Arithmetic

  // Let's create an object called "a" and assign it the number 5
  var a = 5

  // Adding varaibles
  println(a + a)   // check output

  // Reassignment
  a = 10

  println(a)   // check output

  // add and reassign
  a = a + a

  println(a)    // check output

  // Example: Use object names to keep better track of what's going on in your code!
  val myIncome = 100

  val taxRate = 0.1

  val myTaxes = myIncome*taxRate

  // show my tax
  println(myTaxes)

  // So what have we learned?
  // We learned some of the basics of numbers in Scala.
  // We also learned how to do arithmetic and use Scala as a basic calculator.
  // We then wrapped it up with learning about assigning numbers to variables in scala.


  // Relational Operators
  // variables
  val num1 = 50
  val num2 = 30



}

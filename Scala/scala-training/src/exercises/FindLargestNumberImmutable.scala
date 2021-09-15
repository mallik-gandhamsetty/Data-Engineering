package exercises

/* Scala program to create a user define function to
return largest number among two numbers. */

object FindLargestNumberImmutable extends App {
  // function definition
  def getLargestNumber(x: Int, y: Int): Int = {
    // declare an immutable variable to hold the largest number
    val largestNumber = if (x > y) {     // if block is used as an expression
      x
    } else {
      y
    }
    largestNumber
  }

  val a: Int = 10
  val b: Int = 20

  // calling a function
  println("Largest number is: " + getLargestNumber(a,b))

}

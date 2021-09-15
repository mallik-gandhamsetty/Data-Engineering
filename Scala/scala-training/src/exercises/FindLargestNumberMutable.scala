package exercises

object FindLargestNumberMutable extends App {
  // function definition
  def getLargestNumber(x: Int, y: Int): Int ={
    // declare a mutable variable to hold the largest number
    var largestNumber: Int = 0
    if (x > y) {              // if block is used as a statement
      largestNumber = x
    } else {
      largestNumber = y
    }
    largestNumber
  }

  val a: Int = 10
  val b: Int = 20

  // calling a function
  println("Largest number is: " + getLargestNumber(a,b))

}

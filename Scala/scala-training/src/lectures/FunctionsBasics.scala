package lectures

object FunctionsBasics extends App{
  // define once and use many times
  // abstract from developers complex code into one nice block

  def sumTwoNumbers(a: Int, b: Int): Int = {
    a + b
  }

  println(sumTwoNumbers(2,3))   // 5

  println(sumTwoNumbers(20,30))   //50

  println(sumTwoNumbers(200,300))   //500


  def finalPrice(productPrice: Double, discount: Double): Double = {
    productPrice - productPrice*discount
  }

  val tShirt = 30.0
  val tShirtDisc = 0.1

  val tShirt2 = 45.5
  val tShirt2Disc = 0.4

  val suit1 = 28.4
  val suit1Discount = 0.1

  println(finalPrice(tShirt, tShirtDisc))

  println(finalPrice(tShirt2, tShirt2Disc))

  println(finalPrice(suit1, suit1Discount))

  val suit2 = 58.4
  val suit2Discount = 0.3
  println(finalPrice(suit2, suit2Discount))

}

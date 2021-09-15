package lectures

object Interpolation extends App{

  val item = "Pen"

  val itemPrice = 10.0

  val itemQty = 4

  // String Interpolation s"text"
  println(s"Item Price: $itemPrice")

  // No interpolation
  println("Item Price: " + itemPrice)

  println("The price of " + item + " is " + itemPrice)

  // s interpolator
  println(s"The price of $item is $itemPrice")

  println(s"Total price of $itemQty ${item}s is ${itemQty * itemPrice}")

  // raw interpolator
  println("I want to escape literals \\ \\ \\")  // this prints 3 \

  println(raw"I want to escape literals \\ \\ \\") // this prints 6 \

  println(raw"""I want to escape literals " \n \t \\ \\ \\""") // this prints 6 \

  // f- interpolator
  println(math.E)
  println(f"${math.E}%.2f")

  val myName = "Mallik"

  println(s"My name is $myName")

}

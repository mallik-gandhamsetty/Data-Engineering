package lectures

object Functions extends App{

  val a: Int = 7
  val b: Int = 10

  val c = a + b

  println(c)

  // def keyword
  // name of the function
  // (argument: Type, argument2: Type)
  // :
  // Type
  // =
  // {  body }
  // def functionName(argument1: Type, argument2: Type...): Type = { body }

  // Q. Write a program to sum 2 numbers using functions
  // function definition
  // x => a memory location  => 2
  // y => another memory location  => 3
  def sum(x: Int, y: Int): Int = {
    x + y
  }
  // x and y are arguments/paramters

  // function calling => passing values to the function
  println(sum(2,3))
  println(sum(a, b)) //


  // calculator
  // ADD, SUB, MUL, DIV
  def subtract(x: Int, y: Int): Int = x - y
  def multiply(x: Int, y: Int): Int = x * y
  def divide(x: Int, y: Int): Int = x / y

  sum(10, 50)
  multiply( 4, 7)
  subtract(10, 30)
  divide(10, 2)

  sum(10, 98)
  multiply( 77, 7)
  subtract(18181, 30)
  divide(10, 2)

  // use these expression in print or assign it to a variable

  // anonymous functions => no name

  // we can define and use functions in the same line
  // def divide(x: Int, y: Int): Int = x / y
  // { (x: Int, y: Int) => x/y }

  val muFunction1 = { (x: Int, y: Int) => x/y }   // (Int, Int) => Int
  val muFunction2 = { (x: Int, y: Int) => s"$x $y" }  // (Int, Int) => String


  // x => { x.toLowerCase }
  val myFunction3: (Int, Int) => String = { (x, y) =>  s"$x $y" }
  val myFunction4: (Int, Double) => Double = { (x, y) =>  x/y }

  // _ means placeholder
  // first _ gets the value from 1st parameter (INT)
  // second _ gets the value from 2nd parameter (Double)
  val myFunction5: (Int, Double) => Double = { _ / _}


  // recursive functions
  // we divide a problem into smaller problems and solve the smaller problems to solve the bigger problem
  // Factorial of a number = 5!
  // 5 * 4!
  // 5 * 4 * 3! => 20 * 3!
  // 20 * 3 * 2!
  // 60 * 2!
  // 60 * 2 * 1! => 120 * 1
  // 120
  def factorial(num: Int): Int = {
    if (num == 1) {
      1
    } else {
      println(s"$num * factorial(${num - 1})")
      num * factorial(num - 1)
    }
  }

  println(factorial(5))
  // num = 5 => No => 5 * factorial(4)
                          // num = 4 => No => 4 * factorial(3)
                                                  // num = 3 => No => 3 * factorial(2)
                                                                          // num = 2 => No => 2 * factorial(1)
  // Tail Recursion


  // example 2
  def aRepeatedFunc(str: String, n: Int): String = {
    if(n == 1) {
      str
    } else {
      str + " " + aRepeatedFunc(str, n-1)
    }
  }
  println(aRepeatedFunc("Hello", 3))


  // No parameters needed
  def myFunc(): Int = 2000
  def myFunc2: Int = 2000

  println(myFunc())
  println(myFunc)

  println(myFunc2)



  // function with side effects
  // sum of two number => accept 2 numbers and output sum

  var m = 10

  // pure
  def myfunc5(k: Int): Int = {
    m + k
  }
  myfunc5(10) // 10 + 10 = 20
  myfunc5(10)  // 10 + 10 = 20
  myfunc5(10)   // 10 + 10 = 20

  // m = 10

  // impure function because it has side effects.
  // it is affecting the value of m
  def myfunc6(k: Int) = {
    m =  m + k
  }

  myfunc6(10) // 10 + 10 = 20
  myfunc6(10)  // 20 + 10 = 30
  myfunc6(10)   // 30 + 10 = 40



  //default args
  def savePic(name: String, width: Int= 1080, height: Int= 768, format: String = "jpg"): Unit = {
    println("saved pic")
  }

  savePic("my_pic1", 1080, 768, "jpeg")
  savePic("my_pic2")
  savePic("my_pic3")   // default values for hieight, int and format are assumed from func def

  // default args =when not specified during function calls use the default value from the definition

  def saveVideo(format: String = "mp4", name: String, width: Int= 1080, height: Int= 768): Unit = {
    println("saved video")
  }
  //saveVideo(name="my video")

  // if the default args are at the last in definition. then you can omit
  // if they are at begining or somewhere in the middle we cant omit the value while calling
  // there should not bw any non default argument after the default ones to take advantage of default arg substitution

  // named args
  saveVideo(name="my_pic1", width=1080, height=768)

  // named args can be in any order
  saveVideo(height=2200, name="hsbhsc", width=5252, format="mkv")



  // Call By Name and Call By Value

  def cbv(p: Long): Unit = {      // here p is a Long
    println("value of p is: " + p)
    println("value of p is: " + p)
  }

  def cbn(p: => Long): Unit = {    // here p is an anonymous func which doesnt accept any parameter but returns a Long
    println("value of p is: " + p)  // println("value of p is: " + System.nanoTime())
    println("value of p is: " + p)  // println("value of p is: " + System.nanoTime())
  }

  cbv(System.nanoTime())  // cbv(5345001766100) => println("value of p is: " + 5345001766100)
  cbn(System.nanoTime())  // p: => Long .... p: () => Long => cbn(System.nanoTime()).... println("value of p is: " + System.nanoTime())


  // call by name => function refs won't be evaluate until it is used

  def f1(): Int = 1 + f1() // => no break condition

  def f2(x: Int, y: => Int): Unit = println(x)

  f2(30, f1())


  def f3(): Int = 1 + f3()   // => no break condition

  def f4(y: => Int, x: Int): Unit = println(x)

  f4(f3(), 34 * 2)

  //

}

package lectures

object Types extends App{

  val a = 10   //type info ALT + =  or  View > Type Info

  // variable declaration
  // [val | var] variableName : [DataType] = [Initial Value]
  val myVar: Int = 10
  println(myVar)

  val myVar2: Double = 10
  println(myVar2)

  // Byte Type -128 to 127
  val minByteVal: Byte = -128
  val maxByteVal: Byte = 127

  // Short Type -32768 to 32767
  val minShortVal: Short = -32768
  val maxShortVal: Short = 32767

  // int
  val x = 10

  // long
  val l1 = 10L
  val l2: Long = 10  // 10 is int => long
  val l3 = 10l

  // float
  val f1: Float = 10.2f   // comeback
  val f2 = 10.2F    // 10.81717171, 108.81171718
  val f3 = 10.2f

  // double
  val d1 = 10.234
  val d2: Double = 10.234   // type is not needed because of type inference

  val d3 = 10.234D
  val d4 = 10.234d
  val d5 = 1.0234e1   // e1 means 10 to the power of 1
  val d6 = 1.0234E1   // 1.0234 * 10^1 => 10.234

  // char
  val c1 = 'c'
  val c2 = 'a'

  // String
  val s1 = "My name is so and so"   // " represents start and end of string

  val s2 = """hello "World"! How are 'you'?"""  // anything in triple quotes is printed as is

  println(s2)

  val s3 = "He said, \"I am going out\""   // " has special meaning \ is used escape "
  println(s3)

  val s4 = "2\\3"   // next character after \ loses special meaning
  println(s4)

  /*Name: Mallik
    Topic: Scala
    Date: Today
   */
  println("Name: Mallik")
  println("Topic: Scala")
  println("Date: Today")

  // option2
  // \n \r \t
  // \n new line
  // \r carriage return
  // \t tab
  println("Name\t:\tMallik\nTopic\t:\tScala\nDate\t:\tToday")

  println("""Name	:	Mallik
Topic	:	Scala
Date	:	Today""")
  // triple quotes - format output, to avoid escape sequences

  // boolean type: true or false
  // no quotes around true or false
  // all lowercase
  val b1 = true
  val b2 = false

  val B1 = false   // variables are case sensitive

  val num1 = 10
  val num2 = 5
  val b3 = (num1 > num2) // 10 > 5 => true => b3
  print(b3)


  val b4: Boolean = true
  //val b5: Boolean = "true"



}

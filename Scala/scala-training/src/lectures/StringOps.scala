package lectures

object StringOps extends App{

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

  // str => seq of chars
  val str = "Hello, I am learning Scala"

  val str2 = "dataBase"

  println(str.length)
  println(str.split(" ").toList)
  println(str.startsWith("Scala")) // endsWidth
  println(str.toLowerCase)
  println(str.toUpperCase)
  if (str2.toLowerCase == "database") {
    println("Str2 is a database")
  }

  println(str.replace(" ", "-"))

  val aNumString = "2"
//  println('a' + aNumString :+ 'z' )
  println('a'+: aNumString :+ 'z' ) // :+ append   +: prepend

  val b = 'a'+ aNumString + 'z' // => upcasting happens for char =>"a" + aNumString + "z"
  val c = 'a'+: aNumString :+ 'z'// direct addition of char to a collection

    // aba
  val s = "abab"  // is reverse is same as string
  if(s.reverse == s) println("same") else println("not same")

  println(str.take(2))   // takeRight

  // path = c:/users/file/filename.txt => takeRight(3)
  // path.split("//") => get fileName of folder name

  // lists, maps, tuples











}

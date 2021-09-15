package practice

object Variables extends App{
  // Variables are simply a storage location.
  // Every variable is known by its name and stores a value

  // There are two types of variables in scala

  // 1. Mutable Variables:
  // These variables are those variables which allow us to change a value after the declaration of a variable.
  // Mutable variables are defined by using the var keyword.
  // Syntax:
  //        var variableName: DataType = "value"
  var example: String = "I am learning Python"

  example = "I am learning Scala"

  println(example)


  // 2. Immutable Variables:
  // These variables are those variables which do not allow you to change a value after the declaration of a variable.
  // Immutable variables are defined by using the val keyword.
  // Syntax:
  //        val variableName: DataType = "value"

  val sample: String = "I am learning Scala"

  // this fails if uncommented because val cannot be reassigned
  // sample = "This is a test"

  // multiple assignments
  val (name1: String, name2: String) = ("a", "b")
  println(name1)
  println(name2)


  // Type inference
  val x = 40                // Type of x is inferred as int
  val y = "test string"     // Type of y is inferred as String
  val z = 30.2              // Type of z is inferred as Double
  val p = 'c'               // Type of p is inferred as Char
  val q = true              // Type of q is inferred as Boolean


}

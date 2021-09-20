package lectures

object VariablesAndValues extends App{
  // Variables are simply a storage location.
  // Every variable is known by its name and stores a value

  // There are two types of variables in scala

  // 1. Mutable Variables:
  // These variables are those variables which allow us to change a value after the declaration of a variable.
  // Mutable variables are defined by using the var keyword.
  // Syntax:
  //        var variableName: DataType = "value"

  var example: String = "I am learning Python"

  // After initializing it once, you can change it later any number of times
  example = "I am learning Scala"

  println(example)

  // Mutability: assigning sum to an existing variable
  var a = 5
  var b = 10
  a = a + b


  // Though "var" is mutable, only value can be reassigned but not a new Type
  var r = 10

  // this is valid
  r = 12

  // this is invalid and not allowed
  // r = "Hello"


  // 2. Immutable Variables:
  // These variables are those variables which do not allow you to change a value after the initial declaration
  // Immutable variables are defined by using the val keyword.
  // val is similar to the final keyword in Java
  // Syntax:
  //        val variableName: DataType = "value"

  val sample: String = "I am learning Scala"

  // the following line of code fails if uncommented because val cannot be reassigned
  // sample = "This is a test"


  // 3. Type of a variable (val or var)
  // Specifying the type is optional
  // Scala is a statically typed language but has powerful Type Inference
  // Scala Type Inference makes it optional to specify the type of variable
  // Below statements ar one and the same:
  //        val varaiableName : DataType = Value
  //        val variableName = value

  val number = 5 // Type is inferred as Int
  var aSimpleString = "This is a string of characters"  // Type is inferred as String => val aSimpleString: String = "This is a string of characters"


  // 4. Assigning multiple variables
  val (v1: String, v2: String) = ("a", "b")
  println(v1)
  println(v2)

  val (name1: String, name2: String) = ("a", "b")
  println(name1)
  println(name2)

  // 5. Everything in Scala is Case Sensitive including Variables
  // Below variables are all valid and different.
  val myVar = "Hi"
  val myvar = "Hi"
  val Myvar = "Hi"
  val MyVar = "Hi"
  val MYVAR = "Hi"

  // Conclusion:
  // Why does this distinction matter?
  //
  //  - Immutability is an important concept in Scala
  //  - Scala encourages the use of immutable variables to build side-effect free code
  //  - Immutable Variables can help the code execute safely in a concurrent/distruted app
  //
  // Tip: If you know that a variable's state should not change once it is assigned, then declare it immutable
  //      This makes sure that there are no unintentional state changes to the variable
  //      This makes maintaining the code far easier

}

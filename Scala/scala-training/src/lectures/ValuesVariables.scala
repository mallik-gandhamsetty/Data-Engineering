package lectures

object ValuesVariables extends App{
  // variable declaration
  // [val | val] variableName : [DataType] = [Initial Value]

  // Immutability
  // defining a third variable to hold sum of two values
  var x = 5
  var y = 10
  var z = x + y

  // Mutabiliy: assigning sum to existing variable
  var a = 5
  var b = 10
  a = a + b

  // mutable variables
  var s = "Hello World!"

  //After initializing it once, you can change it later.
  s = "Hello Scala!"

  // immutable variables
  // val is similar to the final keyword in Java
  val v = "You cannot change me."
  //v = "Let me try."  //Output:- error: reassignment to val

  // A mutable variable cannot be reassigned a new datatype
  var distance = 10
  distance = 12
  //distance = "a"

  // IN REPL show resXX usage

}

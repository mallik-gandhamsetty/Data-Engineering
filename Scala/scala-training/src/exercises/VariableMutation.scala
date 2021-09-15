package exercises

object VariableMutation {
  def main(args: Array[String]) {
    // variable declaration
    // [val | val] variableName : [DataType] = [Initial Value]
    // Define a third variable to hold sum of two variables
    var x = 5
    var y = 10
    var z = x + y
    println(z)

    /* Mutabiliy: Because var is used to declare mutable variables,
    you can assign sum of these two variables to an existing variable */

    var a = 5
    var b = 10
    a = a + b
    // value of a should be 15 instead of 5
    println(a)
  }
}


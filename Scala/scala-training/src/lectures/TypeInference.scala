package lectures

object  TypeInference extends App{

  // What are Statically Typed and Dynamically Typed Languages
  // Ans:
  //  |---------------------------------|-----------------------------------|
  //  |   Statically Typed Languages    |     Dynamically Typed Languages   |
  //  |---------------------------------|-----------------------------------|
  //  |    Java, C, C++                 |       Python, Javascript          |
  //  |---------------------------------|-----------------------------------|
  //  | The type of each variable is    | The type of every variable is     |
  //  | known at compile time           | known only at runtime             |
  //  |---------------------------------|-----------------------------------|
  //  | Bugs get caught quickly         | Lots of nasty bugs creep in       |
  //  | and easily                      |                                   |
  //  |---------------------------------|-----------------------------------|
  //  | Code is more verbose            | writing code is quick & dirty     |
  //  |---------------------------------|-----------------------------------|

  // Though Scala is a "Statically Typed language", it seeks to get the best of both worlds via Type Inference
  //
  // Scala has a built-in Type Inference system to guess the Type of a value or expression being assigned
  // at compile time. So, Scala code looks more like a Python code but works like Java code in the backend.
  //
  // Because of Type Inference,
  //     Variable types can often be omitted
  //     Function return types can often be omitted

  // Type of variable a is inferred as Int
  val a = 10   //In IntelliJ, to see type info=> select variable and press ALT + =  or  Go to View and the click Type Info

  // Type of variable a is inferred as String
  val b = "Hello"

  // Type of variable C is inferred from return type of the code block
  // The last expression of the block is return value of the code block. So, in this case d.
  // The type of d i inferred as Double. So the type of c is also Double
  val c = {
    val d = 30.0
    d
  }

}

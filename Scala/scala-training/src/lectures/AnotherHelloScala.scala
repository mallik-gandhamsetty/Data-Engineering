package lectures

// App is a "trait" that can be extended to achieve the same effect as using main function in Hello.scala
// The App trait already has a main function defined and by extending it we get access to that function
// so we don't need to define "main" again in our program
object AnotherHelloScala extends App {
  println("Hello Scala!")
}

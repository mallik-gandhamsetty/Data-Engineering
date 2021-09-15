package lectures

object Loops extends App{

  // variable declaration
  var a = 0

  // while loop
  while(a < 10) {
    println("value of a is " + a)
    a = a + 1
  }

  // increment by step
  var b = 0
  while(b < 10) {
    println("value of b is " + b)
    b = b + 2
  }

  // decrement loop variable
  var c = 10
  while(c > 0) {
    println("value of c is " +c)
    c = c - 1
  }

  // do-while
  var d = 0
  do {
    println(d)
    d = d + 1
  } while(d < 10)

  // fail condition - executes atleast once
  var e = 10
  do {
    println("Value of e: " + e)
    e = e + 2
  } while (e < 10)

  // always have a fail condition
  // never miss loop variable management
  // or your loop will never finish

//  var e = 1
//  do {
//    println(e)
//  } while(e > 0)  // this never breaks (lack of loop variable increment)


  // for loop execution with a range
  for( a <- 1 to 10){
    println( "Value of a: " + a );
  }

  // for loop execution up to a range
  for( a <- 1 until 10){
    println( "Value of a: " + a );
  }


  // for loop execution with multiple iterators
  for (a <- 1 to 3) {
    for (b <- 1 to 3) {
      println(s"a -> ${a}    b -> ${b}")
    }
  }


  // concise way
  for( a <- 1 to 3; b <- 1 to 3){
    println(s"a -> ${a}    b -> ${b}")
  }

  // yet another way
  for {
    a <- 1 to 3
    b <- 1 to 3
  } {
    println(s"a -> ${a}    b -> ${b}")
  }

  // eliminate {} if just one statement
  for {
    a <- 1 to 3
    b <- 1 to 3
  } println(s"a -> ${a}    b -> ${b}")

  // for loop execution with a condition
  for( a <- 1 to 20 ){
    if(a % 2 == 0) {
      println(s"${a} is divisible by 2")
    }
  }

  for( a <- 1 to 20 if a % 2 == 0) {
    println(s"${a} is divisible by 2")
  }

  // no need of paranthesis
  for( a <- 1 to 20 if a % 2 == 0) println(s"${a} is divisible by 2")

  // for loop multiple conditions.
  for( a <- 1 to 20
       if a % 2 == 0
       if a > 3) {
    println(s"${a} is divisible by 2 and is greater than 3")
  }

  // for loop with multiple conditions and semi colon
  for( a <- 1 until 20
       if a != 3; if a < 8 ){
    println( "Value of a: " + a );
  }

  // yield

}

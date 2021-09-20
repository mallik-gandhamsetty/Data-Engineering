package lectures

object Emptiness extends App {

  // To understand emptiness in Scala, you should understand 6 things
  //     1. null
  //     2. Null
  //     3. Nothing
  //     4. Nil
  //     5. None
  //     6. Unit

  // 1. null
  // null is basically the same as null in Java
  // null can be assigned to reference types but cannot be assigned to value types

  // assigning null to a string variable
  val x: String = null

  if(x == null) println("null") else println("not null")


  // assigning null to any value type like Int is illegal
  // Below line if uncommented will result in error. error: an expression of type Null is ineligible for implicit conversion
  // val y: Int = null



  // 2. Null
  // Null is a Trait. It is a Type not a value.
  // The literal null is of type Null
  // Null all reference types including the custom classes and traits we define.
  // This allows us to use the null value in place of any reference type.
  // Null is not a subtype of AnyVal.
  // That is why, null:Null cannot be assigned to value types



  // 3. Nothing
  // Nothing is a trait. It is a Type not a value
  // Nothing is a subtype of all types, also called the bottom type. It extends everything
  // That means, Nothing is a subtype of Int and also is a subtype of String and List[User]

  // As per scala docs: “There is no value that has type Nothing”
  // Meaning, there is no such value that can be Nothing.
  // Because, there is no magical value that can be an Int, String and a List[Sometype].
  // So, Nothing can never be instantiated
  // Blog: https://medium.com/@juntomioka/what-is-scalas-nothing-type-for-6d1a1d4bcc02


  // 4. Nil
  // Nil is a special value associated with an empty List
  // Nil is an empty singleton object list that extends the List type,
  // therefore, it has all fields and methods that any other List object has, and it’s usually used to initialize empty lists

  val myList = Nil
  println("a list is initialized with length %s".format(myList.length))


  // Lists are internally represented as Linked Lists and this special value Nil is used to signify the end of the list
  // So, another popular way of creating and populating new lists is using the cons (::) operator and the Nil at the end:
  val myList2 = "A" :: "B" :: Nil

  // All List methods are applicable for Nil as well.
  // Thus, it can be used safely in place of any regular List.
  var someList = List(1,2,3,4)

  while(someList != Nil) {
    println(someList.head)
    someList = someList.tail
  }


  // 5. None
  // None is a subtype of Option Type
  // An Option is a (monadic) collection used to capture presence or absence of a value


}

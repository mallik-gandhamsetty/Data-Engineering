package lectures

object Types extends App{

  // Scala Type System
  // Scala has a Unified Type System
  // https://docs.scala-lang.org/tour/unified-types.html
  //
  // In Scala, all values are instances of a class (no exceptions)
  //
  // Scala's Type System is defined as a hierarchy of classes
  //
  // Any: Any is the supertype of all types, also called the top type.
  //      It defines certain universal methods such as equals, hashCode, and toString.
  //      Every other type in Scala is a subclass of this Scala class.
  //
  //      Any has two direct subclasses: AnyVal and AnyRef.
  //          AnyVal
  //          AnyRef
  //
  // AnyVal: AnyVal represents value types.
  //         There are nine predefined value types and they are non-nullable:
  //               Double, Float, Long, Int, Short, Byte, Char, Unit, and Boolean.
  //         All Value Classes are instantiated using literals.
  //         Literals are things that are already into the Language itself like Numbers, characters, true, false values
  //
  // AnyRef: AnyRef represents reference types.
  //         All non-value types are defined as reference types.
  //         All user-defined types in Scala are also subtypes of AnyRef
  //

  // Value Types
  // ----------------------------------
  // 1. Byte Type has a range of -128 to 127. Occupies 1 Byte of storage in memory
  val a: Byte = -128
  val b: Byte = 127

  // 2. Short Type has range of -32768 to 32767. Occupies 2 Bytes of storage in memory
  val c: Short = -32768
  val d: Short = 32767

  // 3. Int Type has range of -2,147,483,648 to 2,147,483,647. Occupies 4 Bytes of storage in memory
  // If you don’t explicitly specify a type for a number literal, it will default to an Int
  val e: Int = 23456
  val f = 1234

  // 4. Long Type has range of -2^63 to 2^63-1, inclusive. Occupies 8 Bytes of storage in memory
  val l1 = 10L
  val l2: Long = 10  // 10 is int => long
  val l3 = 10l

  // 5. Double is stored as 64-bit IEEE 754 double-precision float
  // Double has 4.94065645841246544e-324d to 1.79769313486231570e+308d as range
  // For decimal literals, the default type is Double.
  val d1 = 10.234
  val d2: Double = 10.234

  val d3 = 10.234D    // Suffix D tell Scala to treat this number as a Double
  val d4 = 10.234d
  val d5 = 1.0234e1   // e1 means 10 to the power of 1
  val d6 = 1.0234E1   // 1.0234 * 10^1 => 10.234

  // 6. Float is stored as 32-bit IEEE 754 single-precision float
  // Float has a range of 1.40129846432481707e-45 to 3.40282346638528860e+38
  val f1: Float = 10.2f
  val f2 = 10.2F
  val f3 = 10.2f

  // 7. Char is stored as 2 Byte unsigned integer ranging (0 to 2^16-1, inclusive) i.e., 0 to 65,535
  // Char is used to store Unicode characters
  val c1 = 'c'
  val c2 = 'a'

  // 8.Boolean Type: Can accept only two literals - true or false
  //  No quotes around true or false and should be in lowercase
  val b1: Boolean = true
  val b2 = false


  // AnyRef: Any is extended to a sub class named AnyRef to represent all Reference Classes
  // All non-value types are defined as reference types.
  // Examples: Collections, Classes and Strings are all of AnyRef Type.

  // Demo Types
  // Consider below functions
  def printAny(x: Any) = println(x)

  def printAnyVal(x: AnyVal) = println(x)

  def printAnyRef(x: AnyRef) = println(x)

  //define below variables
  val someVal = 5             // Int is a subtype of Anyval

  val someRef = new Object   // Any class/object is a subtype of AnyRef type (Don't worry about what is new Object)

  // printAny works with both
  printAny(someVal)
  printAny(someRef)

  // printAnyVal works with only Value Types
  printAnyVal(someVal)
  // uncomment below line and you would see the error
  // error: type mismatch;
  //    found   : Object
  //    required: AnyVal

  // printAnyVal(someRef)


  // printAnyRef works with only Ref Types
  printAnyRef(someRef)

  // uncomment below line and you would see the error
  // error: the result type of an implicit conversion must be more specific than AnyRef

  // printAnyRef(someRef)


  // Nothing Type
  // Nothing is a subtype of all types, also called the bottom type. It extends everything
  // That means, Nothing is a subtype of Int and also is a subtype of String and List[User]
  // Blog: https://medium.com/@juntomioka/what-is-scalas-nothing-type-for-6d1a1d4bcc02



  // Null Type
  // Null is the type of the literal "null". It is a subtype of every type except those of value classes.
  // Meaning Null Type is a subtype of AnyRef
  // Since Null is not a subtype of value types, null is not a member of any such type.

  // So, it is not possible to assign null to a variable of type Int or Boolean nut can be assigned to a string

  val s: String = null // Here s is a string and null is assigned to it. null assigned here is of type Null.

  // Null is a Type
  // null is a literal of type Null

}

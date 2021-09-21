package lectures

object Lists extends App{

  // Lists
  // A list is an immutable singly linked list
  // The end of each List is indicated by the special value Nil
  // Nil is basically an empty list - it is a singleton objet of type List[Nothing]

  // Create a list using the special value Nil and cons operator ::
  val weekDays1 = "mon" :: "tue" :: "wed" :: "thu" :: "fri" :: Nil

  // the cons operator (::) takes a list (initially Nil) and then adds each element to the head of that list
  // "fri" -> Nil
  // "thu" -> "fri" -> Nil
  // and so on
  // "mon" -> "tue" -> "wed" -> "thu" -> "fri" -> Nil

  // An equivalent way to create a list is to use the List constructor (or called as Class parameters)
  val weekDays = List("Mon", "Tue", "Wed", "Thu", "Fri")
  val weekEnds = List("Sat", "Sun")

  // Concatenation of Two lists
  val days1 = weekDays ++ weekDays
  val days2 = weekDays ::: weekEnds

  // ::: vs ++
  // ::: can only be used with Lists
  // ++ can be used with any Traversable

  // Note-  ::: and :: are both right associative.


  // Zip two Lists
  // A zip creates a key value pair (tuple) of corresponding elements
  val allDays = List("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
  val dayIndices = List(1,2,3,4,5,6,7)

  val daysZip = dayIndices zip allDays   // this is same as dayIndices.zip(allDays)



  // Flatten nested Lists
  val nestedDays = List(weekDays, weekEnds)
  val flatDays = nestedDays.flatten



  // head
  println(allDays.head)        //try this on nested list and observe the output

  // tail
  println(allDays.tail)        //try this on nested listand observe the output

  // size
  println(allDays.size)

  // reverse
  println(allDays.reverse)     //try this on nested list and observe the output

  // contains: tests for existence of a specific element and returns boolean
  println(weekDays.contains("Mon"))
  println(weekDays.contains("Sat"))

  // in operator notation
  println(weekDays contains "Mon")
  println(weekDays contains "Sat")

  // Looping Lists
  // for loop
  for (c <- weekDays) println(c)

  // while, var and isEmpty
  // you need a mutable handler for the list
  var workDays = weekDays

  while(! workDays.isEmpty) { // isEmpty returns true if no elements in the list
    println(s"Uh oh..Today is ${workDays.head}. ${workDays.size} days left for the weekend")
    workDays = workDays.tail  // except the head element, remaining are assigned to workdays variable
  }

  // using while var and Nil
  var restOfWeek = weekDays

  while(restOfWeek != Nil) { // Nil means no elements in the list
    println(s"Uh oh..Today is ${restOfWeek.head}. ${restOfWeek.size} days left for the weekend")
    restOfWeek = restOfWeek.tail  // except the head element, remaining are assigned to workdays variable
  }

  // distinct
  val d = List("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Wed", "Tue")

  println(d)
  println(d.distinct)

  // drop
  println(d.drop(2))  // drops 2 elements

  println(d drop 2)  // drops 2 elements. using operator notation


  // slice: returns a new list, subset of the old one
  println(weekDays slice (2,4))   // try this in dot notation

  // splitAt: returns 2 new lists
  println(weekDays.splitAt(2))  // try this with diff numbers and observe output

  // take: returns a new list starting at the head
  println(weekDays take 2)   // try this in dot notation

  // sorted: sorted returns a new list sorted in "natural" order
  println(weekDays.sorted)

  // endsWith
  println(allDays.endsWith(weekEnds)) // endswith checks if alldays list is ending with elements same as in the second list weekends
  println(allDays endsWith weekEnds)  // same thng in operator notation

  // startsWith
  println(allDays.startsWith(weekDays)) // startsWith checks if alldays list is starting with elements same as in the second list weekDays
  println(allDays startsWith weekDays)  // same thng in operator notation


  // other operations would work as you expect them to
  // .sum, .product, .min, .max
  // Try these on a list with strings and on a list with numbers

  // Higher Order Functions:
  // Functions that take other functions as arguments or return functions are called HOFs
  // Higher Order Functions are very important in Functional Programming
  //
  // Few important Higher Order Functions
  //    1. foreach
  //    2. map
  //    3. filter
  //    4. partition
  //    5. sortBy
  //    6. fold
  //    7. scan
  //    8. reduce
  //
  // How do these functions work?
  //
  // All these functions take a function and apply it to a list
  // Example:
  //      map on list takes a function and applies it to all the elements of the list and yields another list
  //      reduce on the other hands shrinks the lists into value


  // 1. foreach
  // foreach takes a procedure and applies it to each element in the list.
  // FYI. A procedure is a block of code that does not return anything
  // This means, foreach also doesn't return any value
  // So, foreach is a statement

  // foreach with println function
  // all three variations are one and the same
  weekDays.foreach(element => println(element))     // for each element of the weekdays list, println function is called
  weekDays.foreach(println(_))
  weekDays foreach println

  // foreach can accept any function or function literal that doesn't return a value
  def printAny(x: Any): Unit = println(s"Current Value is $x")

  // function literal
  val printSome = (x: Any) => { println(s"Printing element $x") }

  // calling foreach by passing these two funcs
  weekDays.foreach(printAny)
  weekDays.foreach(printSome)


  // 2. .map
  // map takes a function and applies it to each element in the list.
  // map returns a value

  // example 1:
  def isMonday(day: String) = {
    day == "Mon"
  }
  // map with a function. All 3 statements are same
  // all 3 will print a list of boolean values.
  println(weekDays.map(ele => isMonday(ele)))
  println(weekDays.map(isMonday(_)))
  println(weekDays.map(isMonday))

  // map with a anonymous function
  println(weekDays.map(ele => ele == "Mon"))
  println(weekDays.map(_ == "Mon"))

  // map with a function literal
  val isManicMonday = (x: String) => { x == "Mon" }
  println(weekDays.map(isManicMonday))


  // example 2:
  val marksList = List(10,29,45,87,31,9,92,23,22,29,31)
  def isPassed(score: Int): String = {
    if (score > 40) {
      "passed"
    } else {
      "failed"
    }
  }
  // function literal
  val passOrFail = (score: Int) => { if(score > 40) "passed" else "failed" }

  // map with a function
  val passedScores = marksList.map(isPassed)
  println(passedScores)

  // map with anonymous funcs
  val passedScores2 = marksList.map(score => if (score > 40) "passed" else "failed")
  println(passedScores2)

  // map with func literal
  val passedScores3 = marksList.map(passOrFail)
  println(passedScores3)



  // 3. .filter
  // Filter takes a predicate and returns each element in that list that satisfies the predicate
  // So, filter expects a function that returns true or false

  // filter with a function. All 3 statements are same
  // all 3 will print a list of values that have satisfied the condition.
  println(weekDays.filter(ele => isMonday(ele)))
  println(weekDays.filter(isMonday(_)))
  println(weekDays.filter(isMonday))

  // filter with a anonymous function
  println(weekDays.filter(ele => ele == "Mon"))
  println(weekDays.filter(_ == "Mon"))

  // filter with a function literal
  println(weekDays.filter(isManicMonday))



  // 4. .partition
  // Takes a predicate and returns 2 lists - one that satisfies and one that don't
  // filter with a function
  println(weekDays.partition(isMonday))

  // filter with a anonymous function
  println(weekDays.partition(_ == "Mon"))

  // filter with a function literal
  println(weekDays.partition(isManicMonday))



  // 5. .sortBy
  // Takes a function and sorts the list elements based on it

  weekDays.sortBy(_(0)) // o means the first char of each string element

  weekDays.sortBy(_(2))  // sort by 3 char of each element

  weekDays.sortBy(_.toLowerCase)  // sort after converting everything to lowercase







  // map => list of records, i might apply transformation and want collection back
  // map(toLowerCase)

  // foreach => writeToDatabase(record)

}

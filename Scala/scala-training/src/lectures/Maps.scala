package lectures

object Maps extends App{

  // A Map is a collection of Key Value Pairs or Tuples

  // create a map from a key value pair
  val stateCodes = Map(
    "California" -> "CA",           // use a key -> value notation to specify a Key value pair in a map
    "New York" -> "NY",
    ("Vermont", "VT")              // use a (key, val) notation to specify a Key value pair in a map
  )

  // lookup the value for a given key
  println(stateCodes("Vermont"))    // MapVariable(key) will return the value corresponding to a given key

  // A non existent key will yield an error
  println(stateCodes("Georgia"))   // this will error out "key not found: Georgia"

  // check the presence of a given key. Contains returns true or false
  println(stateCodes.contains("California"))

  // combine both
  if(stateCodes.contains("Georgia")) println(stateCodes("Georgia")) else println("No such key Georgia in the map")

  // apply higher order functions on Map
  // foreach map and other higher order functions work just as they will pn Lists
  // but the function you specify must operate on Key-value pairs ie., 2 element tuples instead of one value like in lists

  // example
  stateCodes.foreach(element => println(element._1  + " = " + element._2))
  stateCodes.foreach((e: (String, String)) => println(e._1  + " = " + e._2))    // (e: (String, String)) is a 2 element Tuple

  // assignment:
  //  1. Apply a filter on map to get only NY code
  //  2. Apply a map function on stateCodes Map to convert all values to lowercase
  //     and return output as Map("California" -> "ca", "New York" -> "ny", "Vermont" -> "vt")

  // convert from a List
  val states = List("California", "New York", "Vermont")
  val codes = List("CA", "NY", "VT")

  val statesAndCodes = (states.zip(codes)).toMap  // zip creates List of 2-tuples and toMap converts 2-tuples List to Map

  // check if same
  println(stateCodes == statesAndCodes)


  // convert to a List
  val s = stateCodes.keySet.toList
  val c = stateCodes.values.toList

}

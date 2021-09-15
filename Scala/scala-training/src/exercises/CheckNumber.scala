package exercises

// Scala program to find a number is positive, negative or positive

object CheckNumber {
  def main(args: Array[String]) {

    // declare a variable
    var number = (-100);

    if(number == 0){                   // == checks for equality
      println("number is zero");
    }
    else if(number > 0){
      println("number is positive");
    }
    else{
      println("number is negative");
    }
  }
}
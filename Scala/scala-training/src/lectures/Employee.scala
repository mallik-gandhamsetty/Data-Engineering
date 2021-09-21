package lectures

object Main extends App{
  val bob = Employee("bob", "taylor", "HR")
  val amy = Employee("amy", "jones", 100, "IT")
  println(bob.getDept)
  println(amy.getDept)

}

object Employee{
  private val deptMap = Map(
    "HR" -> "Human Resources",
    "IT" -> "Information Tech"
  )

  def apply(first: String, last:String, dept: String) = new Employee(first,last,100, deptMap(dept))
  def apply(first: String, last: String, stocks: Int, dept: String) = new Employee(first,last,stocks, deptMap(dept))

}

class Employee (f: String, l: String, s: Int, d: String){
  private val first: String = f
  private val last: String = l
  private var stocks: Int = s
  private val dept: String = d

  def getFirst: String = first
  def getLast: String = last
  def getStocks: Int = stocks
  def getDept: String = dept

  def awardMoreStocks(numberOfStocks: Int): Unit = stocks += numberOfStocks

  override def toString: String = first + " " + last + " " + stocks + " " + dept

}







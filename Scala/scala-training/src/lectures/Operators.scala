package lectures

object Operators extends App{

  // operators
  val a: Int = 10
  val b: Int = 20
  val c: Int = 25
  val d: Int = 25

  // arithmetic
  println("a + b = " + (a + b))
  println("a - b = " + (a - b))
  println("a * b = " + (a * b))
  println("b / a = " + (b / a))
  println("b % a = " + (b % a))
  println("c % a = " + (c % a))

  // relational
  println("a == b = " + (a == b))
  println("a != b = " + (a != b))
  println("a > b = " + (a > b))
  println("a < b = " + (a < b))
  println("b >= a = " + (b >= a))
  println("b <= a = " + (b <= a))

  // logical operators
  val t: Boolean = true
  val f: Boolean = false

  println("t && f = " + (t && f))

  println("t || f = " + (t || f))

  println("!(t && f) = " + !((t && f)))

  // bitwise
  val p: Int = 60 /* 60 = 0011 1100 */
  val q: Int = 13 /* 13 = 0000 1101 */
  var r: Int = 0

  r = p & q /* 12 = 0000 1100 */
  println("p & q = " + r)

  r = p | q /* 61 = 0011 1101 */
  println("p | q = " + r)

  r = p ^ q /* 49 = 0011 0001 */
  println("p ^ q = " + r)

  r = ~(p) /* -61 = 1100 0011 */
  println("~p = " + r)

  r = p << 2 /* 240 = 1111 0000 */
  println("p << 2 = " + r)

  r = p >> 2 /* 215 = 1111 */
  println("p >> 2  = " + r)

  r = p >>> 2 /* 215 = 0000 1111 */
  println("p >>> 2 = " + r)


  // assignment operators
  var x: Int = 10
  val y: Int = 20
  var z: Int = 0

  z = x + y
  println("z = x + y  = " + z)

  z += x
  println("z += x  = " + z)

  z -= x
  println("z -= x = " + z)

  z *= x
  println("z *= x = " + z)

  x = 10
  z = 15
  z /= x
  println("z /= x  = " + z)

  x = 10
  z = 15
  z %= x
  println("z %= x  = " + z)

  z <<= 2
  println("z <<= 2  = " + z)

  z >>= 2
  println("z >>= 2  = " + z)

  z >>= 2
  println("z >>= 2  = " + z)

  z &= x
  println("z &= x  = " + z)

  z ^= x
  println("z ^= x  = " + z)

  z |= x
  println("z |= x  = " + z)





}

package lectures

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, Dataset, Encoders, SparkSession}

import java.sql.Date

object DSLecture01 extends App{

  val spark = SparkSession.builder()
    .appName("DS Lecture 01 - Dataset Basics")
    .config("spark.master", "local")
    .getOrCreate()

  // Create a dataset from dataframe consisting of simple data type
  // Example: converting a dataframe which has one column of Int type

  // create a dataframe
  val numbersDF: DataFrame = spark.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load("src/main/resources/data/numbers.csv")

  numbersDF.printSchema()

  // To convert a DF to a Dataset we need encoders
  // Encoders are basically used to convert a JVM object of type T to and from the internal Spark SQL representation.
  // Encoders are highly specialized and optimized code generators that generate custom bytecode for serialization and deserialization of your data.

  // Encoders are limited to and optimal for primitives like Int, double etc, case classes and Spark SQL data types.
  // They contains schema information, which makes these highly optimized code generators possible,
  // and enables optimization based on the shape of the data.
  // Since Spark understands the structure of data in Datasets, it can create a more optimal layout in memory when caching Datasets.
  // >10x faster than Kryo serialization (Java serialization is orders of magnitude slower than Kryo)

  // Encoders are generally created automatically through implicits from a SparkSession,
  // or can be explicitly created by calling static methods on Encoders like below

  // Explicit usage of encoder
  val numbersDS: Dataset[Int] = numbersDF.as[Int](Encoders.scalaInt)

  // Automatic usage of encoders through implicits from a SparkSession
  import spark.implicits._
  val numDS: Dataset[Int] = numbersDF.as[Int]  // encoder is provided by the import

  // this is also possible because of spark implicit import
  val ds = Seq(1, 2, 3).toDS()

  ds.show()

  // dataset of a complex type
  // 1 - define your case class
  case class Car(
                  Name: String,
                  Miles_per_Gallon: Option[Double],
                  Cylinders: Long,
                  Displacement: Double,
                  Horsepower: Option[Long],
                  Weight_in_lbs: Long,
                  Acceleration: Double,
                  Year: Date,
                  Origin: String
                )

  // 2 - read the DF from the file
  def readDF(filename: String) = spark.read
    .option("inferSchema", "true")
    .json(s"src/main/resources/data/$filename")

  val carsDF = readDF("cars.json")

  // 3 - define an encoder (importing the implicits)
  import spark.implicits._

  // 4 - convert the DF to DS
  val carsDS = carsDF.as[Car]

  // 5. A dataset enables all types of functional operations like a regular scala collection

  // filter with an anonymous function. This won't work on a dataframe
  numbersDS.filter(_ < 100)

  // map, flatMap, fold, reduce, for comprehensions ...
  val carNamesDS = carsDS.map(car => car.Name.toUpperCase())

  // Grouping a dataset
  val carsGroupedByOrigin = carsDS
    .groupByKey(_.Origin)
    .count()

  carsGroupedByOrigin.show

  // Datasets support all DF functions!
  carsDS.groupBy(col("Origin")).count().show

  // grouping is a WIDE transformations, will involve SHUFFLE operation



  //  read.textFile method returns a Dataset[String]
  val strDS = spark.read.textFile("src/main/resources/data/sampleTextFile.txt")
  strDS.printSchema
  strDS.show


}

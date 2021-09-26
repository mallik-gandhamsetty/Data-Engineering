package lectures

import org.apache.spark.sql.SparkSession

object DFLecture04 extends App{

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")   // "local[2]"
    .getOrCreate()

  // create dataframe using a Sequence of Tuples
  // Each Tuple represents a Row in the DataFrame

  // create DF from tuples
  val cars = Seq(
    ("chevrolet chevelle malibu",18,8,307,130,3504,12.0,"1970-01-01","USA"),
    ("buick skylark 320",15,8,350,165,3693,11.5,"1970-01-01","USA"),
    ("plymouth satellite",18,8,318,150,3436,11.0,"1970-01-01","USA"),
    ("amc rebel sst",16,8,304,150,3433,12.0,"1970-01-01","USA"),
    ("ford torino",17,8,302,140,3449,10.5,"1970-01-01","USA"),
    ("ford galaxie 500",15,8,429,198,4341,10.0,"1970-01-01","USA"),
    ("chevrolet impala",14,8,454,220,4354,9.0,"1970-01-01","USA"),
    ("plymouth fury iii",14,8,440,215,4312,8.5,"1970-01-01","USA"),
    ("pontiac catalina",14,8,455,225,4425,10.0,"1970-01-01","USA"),
    ("amc ambassador dpl",15,8,390,190,3850,8.5,"1970-01-01","USA")
  )


  // Method 1: Using createDataFrame method
  val carsDF = spark.createDataFrame(cars)   // schema is auto-inferred but names need to be provided
  carsDF.show

  carsDF.printSchema

  // create cars DF with implicits
  import spark.implicits._

  // Method 2: Using toDF (needs above spark implicits import)
  val carsDF2 = cars.toDF("Name", "MPG", "Cylinders", "Displacement", "HP", "Weight", "Acceleration", "Year", "CountryOrigin")

  carsDF2.show

  carsDF2.printSchema


  // Finish Exercises 01 and 02

}

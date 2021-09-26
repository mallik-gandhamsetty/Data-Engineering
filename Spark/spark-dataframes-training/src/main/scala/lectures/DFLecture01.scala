package lectures

import org.apache.spark.sql.SparkSession

object DFLecture01 extends App{

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")   // "local[2]"
    .getOrCreate()


  // reading a DF
  val firstDF = spark.read
    .format("json")
    .option("inferSchema", "true")
    .load("src/main/resources/data/cars.json")


  // showing a DF
  firstDF.show()

  // printing DF schema
  firstDF.printSchema()

  // print schema as StructType
  println(firstDF.schema)

  // print schema as DDL String
  println(firstDF.schema.toDDL)

  // get rows
  firstDF.take(10).foreach(println)    // df.take(10) returns Array of rows to the driver

}

package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object DFLecture02 extends App{

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DataFrames Basics")
    .config("spark.master", "local")   // "local[2]"
    .getOrCreate()

  // look at the data file from the data folder and analyze the structure and
  // define a schema programmatically
  val carsSchema = StructType(Array(
    StructField("Name", StringType),
    StructField("Miles_per_Gallon", DoubleType),
    StructField("Cylinders", LongType),
    StructField("Displacement", DoubleType),
    StructField("Horsepower", LongType),
    StructField("Weight_in_lbs", LongType),
    StructField("Acceleration", DoubleType),
    StructField("Year", StringType),
    StructField("Origin", StringType)
  ))

  // read a DF with your schema
  val carsDFWithSchema = spark.read
    .format("json")
    .schema(carsSchema)
    .load("src/main/resources/data/cars.json")


  // showing a DF
  carsDFWithSchema.show()

  // printing DF schema
  carsDFWithSchema.printSchema()


}

package lectures

import org.apache.spark.sql.functions.{col, column, expr}
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SaveMode, SparkSession}

object DFLecture06 extends App{

  /**
   * What we cover in this app:
   * - Columns and Expressions
   */

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DF Columns and Expressions")
    .config("spark.master", "local")   // "local[2]"
    .getOrCreate()

  // Reading from JSON files
  val carsSchema = StructType(Array(
    StructField("Name", StringType),
    StructField("Miles_per_Gallon", DoubleType),
    StructField("Cylinders", LongType),
    StructField("Displacement", DoubleType),
    StructField("Horsepower", LongType),
    StructField("Weight_in_lbs", LongType),
    StructField("Acceleration", DoubleType),
    StructField("Year", DateType),
    StructField("Origin", StringType)
  ))

  // Reading a DF
  val carsDF = spark.read
    .schema(carsSchema)                   // enforce a schema
    .json("src/main/resources/data/cars.json")

  // Get all columns
  carsDF.columns

  // selecting (projecting) using a column name (string)
  carsDF.select("Name").show

  // or use col method (org.apache.spark.sql.functions.col) directly
  carsDF.select(col("Name")).show

  // or use col method of a Dataframe
  carsDF.select(carsDF.col("Name")).show

  // or use implicits
  import spark.implicits._
  carsDF.select($"Name").show

  // or use column method (org.apache.spark.sql.functions.col) directly
  carsDF.select(column("Name")).show


  // Summary: Multiple ways to select from a Dataframe by specifying a Column type object
  carsDF.select(
    carsDF.col("Name"),
    col("Acceleration"),
    column("Weight_in_lbs"),
    'Year,        // Scala Symbol notation auto-converts to column
    $"Horsepower", // $ interpolated string returns a Column object
    expr("Origin") // expr method returns a column object
  ).show

  // Summary: Multiple ways to select from a Dataframe by specifying Columns as Strings
  carsDF.select("Name", "Year").show

  // Expressions
  val weightInLbs = carsDF.col("Weight_in_lbs")

  val carsWithExprDF = carsDF.select(
    col("Name"),
    weightInLbs,
    // method 1
    (carsDF.col("Weight_in_lbs") / 2.2).as("Weight_in_kg"),
    // method 2 using expr
    expr("Weight_in_lbs / 2.2").as("Weight_in_kg_2")
  )

  // expr is a very popular way of defining expressions.
  // Spark introduced a method - selectExpr to treat everything as expressions
  val carsWithSelectExprDF = carsDF.selectExpr(
    "Name",
    "Weight_in_lbs",
    "Weight_in_lbs / 2.2"
  )

  // Processing a Dataframe
  // adding a column
  val carsWithKg3DF = carsDF.withColumn("Weight_in_kg_3", col("Weight_in_lbs") / 2.2)

  // renaming a column
  val carsWithColumnRenamed = carsDF.withColumnRenamed("Weight_in_lbs", "Weight in pounds")

  // if a column contains spaces, enclose the whole column name in back quotes `column with space` as below
  carsWithColumnRenamed.selectExpr("`Weight in pounds`")

  // remove a column
  carsWithColumnRenamed.drop("Cylinders", "Displacement")

  // filter method is overloaded - you can use a operator style filter or expression style
  // In column operator notation,
  //        === operator means equality check
  //        =!= operator means inequality check
  val europeanCarsDF = carsDF.filter(col("Origin") =!= "USA")
  val europeanCarsDF2 = carsDF.where(col("Origin") =!= "USA")

  // filtering with expression strings
  // You can use SQL like filtering expression as well
  val americanCarsDF = carsDF.filter("Origin = 'USA'")

  // you can chain filters using multiple .filter expressions
  val americanPowerfulCarsDF = carsDF.filter(col("Origin") === "USA")
                                     .filter(col("Horsepower") > 150)

  // or you can use logical operators
  val americanPowerfulCarsDF2 = carsDF.filter(col("Origin") === "USA" and col("Horsepower") > 150)
  val americanPowerfulCarsDF3 = carsDF.filter("Origin = 'USA' and Horsepower > 150")

  // union = adding rows from two dataframes vertically
  // Ex: if df1 has 5 rows and df2 has 10 rows, df1.union(df2) returns 15 rows
  // Union works only if df1 and df2 have same schema
  val moreCarsDF = spark.read.option("inferSchema", "true").json("src/main/resources/data/more_cars.json")
  val allCarsDF = carsDF.union(moreCarsDF)

  // distinct values
  val allCountriesDF = carsDF.select("Origin").distinct()


  // Finish DFEx04

}

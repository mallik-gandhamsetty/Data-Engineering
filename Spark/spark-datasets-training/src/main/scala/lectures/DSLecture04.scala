package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DSLecture04 extends App{

  val spark = SparkSession.builder()
    .appName("DS Lecture 04 - Complex Types")
    .config("spark.master", "local")
    .getOrCreate()

  val moviesDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/movies.json")

  // Dates
  val moviesWithReleaseDates = moviesDF
    .select(col("Title"), to_date(col("Release_Date"), "dd-MMM-yy").as("Actual_Release")) // conversion


  moviesWithReleaseDates
    .withColumn("Today", current_date()) // today
    .withColumn("Right_Now", current_timestamp()) // this second
    .withColumn("Movie_Age", datediff(col("Today"), col("Actual_Release")) / 365) // explore date_add, date_sub functions


  moviesWithReleaseDates.select("*").where(col("Actual_Release").isNull)

  // Struct Types (Json Objects)

  // 1 - create a complex column using col
  val complexMoviesDF1 = moviesDF
    .select(col("Title"), struct(col("US_Gross"), col("Worldwide_Gross")).as("Profit"))

  complexMoviesDF1.show

  // using selectExpr
  val complexMoviesDF2 = moviesDF
    .selectExpr("Title", "(US_Gross, Worldwide_Gross) as Profit")

  complexMoviesDF2.show

  //selecting attributes from complex column using getField
  complexMoviesDF1
    .select(col("Title"), col("Profit").getField("US_Gross").as("US_Profit"))

  //selecting attributes from complex column using dot operator and selectExpr
  complexMoviesDF2
    .selectExpr("Title", "Profit.US_Gross")



  // Arrays
  val moviesTitleWordsDF = moviesDF.select(col("Title"), split(col("Title"), " |,").as("Title_Words")) // ARRAY of strings

  moviesTitleWordsDF.select(
    col("Title"),
    expr("Title_Words[0]"),           // indexing
    size(col("Title_Words")),      // array size
    array_contains(col("Title_Words"), "Love") // look for a value in an array column
  )


}

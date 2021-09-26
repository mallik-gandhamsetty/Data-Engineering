package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DSLecture05 extends App{

  val spark = SparkSession.builder()
    .appName("DS Lecture 05 - Nulls")
    .config("spark.master", "local")
    .getOrCreate()

  val moviesDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/movies.json")

  // select the first non-null value using coalesce function
  moviesDF.select(
    col("Title"),
    col("Rotten_Tomatoes_Rating"),
    col("IMDB_Rating"),
    coalesce(col("Rotten_Tomatoes_Rating"), col("IMDB_Rating") * 10)
  )

  // checking for nulls in a column using isNull
  moviesDF.select("*").where(col("Rotten_Tomatoes_Rating").isNull)

  // nulls when ordering
  moviesDF.orderBy(col("IMDB_Rating").desc_nulls_last)

  // drop rows having nulls
  moviesDF.select("Title", "IMDB_Rating").na.drop() // remove rows containing nulls

  // replace nulls in numeric columns specified
  moviesDF.na.fill(0, List("IMDB_Rating", "Rotten_Tomatoes_Rating"))

  // replace nulls in numeric columns with a specific value
  moviesDF.na.fill(Map(
    "IMDB_Rating" -> 0,
    "Rotten_Tomatoes_Rating" -> 10,
    "Director" -> "Unknown"
  ))

  // complex operations using selectExpr
  moviesDF.selectExpr(
    "Title",
    "IMDB_Rating",
    "Rotten_Tomatoes_Rating",
    "ifnull(Rotten_Tomatoes_Rating, IMDB_Rating * 10) as ifnull", // same as coalesce
    "nvl(Rotten_Tomatoes_Rating, IMDB_Rating * 10) as nvl", // nvl(expr1, expr2) - Returns expr2 if expr1 is null, or expr1 otherwise.
    "nvl2(Rotten_Tomatoes_Rating, IMDB_Rating * 10, 0.0) as nvl2", // nvl2(expr1, expr2, expr3) - if expr1 is not null return expr2 else expr3.
    "nullif(Rotten_Tomatoes_Rating, IMDB_Rating * 10) as nullif" // nullif(expr1, expr2) - Returns null if expr1 equals to expr2, or expr1 otherwise.
  ).show()


}

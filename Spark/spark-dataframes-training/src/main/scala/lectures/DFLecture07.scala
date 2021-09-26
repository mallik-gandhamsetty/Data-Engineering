package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DFLecture07 extends App{

  /**
   * What we cover in this app:
   * - Aggregations
   */

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("Aggregations and Grouping")
    .config("spark.master", "local")
    .getOrCreate()

  val moviesDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/movies.json")

  // counting non null rows in a dataframe in 2 notations
  moviesDF.select(count(col("Major_Genre"))).show
  moviesDF.selectExpr("count(Major_Genre)").show

  // counting all rows - usage of * in count will INCLUDE nulls
  moviesDF.select(count("*")).show

  // counting distinct
  moviesDF.select(countDistinct(col("Major_Genre"))).show()

  // approximate count
  moviesDF.select(approx_count_distinct(col("Major_Genre"))).show

  // min and max
  moviesDF.select(min(col("IMDB_Rating"))).show
  moviesDF.selectExpr("min(IMDB_Rating)").show

  // sum
  moviesDF.select(sum(col("US_Gross"))).show
  moviesDF.selectExpr("sum(US_Gross)").show

  // avg
  moviesDF.select(avg(col("Rotten_Tomatoes_Rating"))).show
  moviesDF.selectExpr("avg(Rotten_Tomatoes_Rating)").show

  // data science
  moviesDF.select(
    mean(col("Rotten_Tomatoes_Rating")),
    stddev(col("Rotten_Tomatoes_Rating"))
  ).show

  // Grouping
  val countByGenreDF = moviesDF
    .groupBy(col("Major_Genre")) // includes null
    .count()  // select count(*) from moviesDF group by Major_Genre

  val avgRatingByGenreDF = moviesDF
    .groupBy(col("Major_Genre"))
    .avg("IMDB_Rating")

  // using multiple aggregations in single group by
  val aggregationsByGenreDF = moviesDF
    .groupBy(col("Major_Genre"))
    .agg(
      count("*").as("N_Movies"),
      avg("IMDB_Rating").as("Avg_Rating")
    )
    .orderBy(col("Avg_Rating"))


  // Finish DFEx05

}

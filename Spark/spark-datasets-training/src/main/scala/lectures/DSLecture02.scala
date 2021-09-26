package lectures

import org.apache.spark.sql.{DataFrame, Dataset, Encoders, SparkSession}

import java.sql.Date

object DSLecture02 extends App{

  val spark = SparkSession.builder()
    .appName("DS Lecture 02 - Dataset Joins")
    .config("spark.master", "local")
    .getOrCreate()

  // 2 - read the DF from the file
  def readDF(filename: String): DataFrame = {
    spark.read
      .option("inferSchema", "true")
      .json(s"src/main/resources/data/$filename")
  }

  // define case classes
  case class Guitar(id: Long, make: String, model: String, guitarType: String)
  case class GuitarPlayer(id: Long, name: String, guitars: Seq[Long], band: Long)
  case class Band(id: Long, name: String, hometown: String, year: Long)

  // import implicits
  import spark.implicits._

  // Create Datasets
  val guitarsDS = readDF("guitars.json").as[Guitar]
  val guitarPlayersDS = readDF("guitarPlayers.json").as[GuitarPlayer]
  val bandsDS = readDF("bands.json").as[Band]

  // Join guitarPlayersDS with bandsDS
  val guitarPlayerBandsDS: Dataset[(GuitarPlayer, Band)] = guitarPlayersDS.joinWith(bandsDS, guitarPlayersDS.col("band") === bandsDS.col("id"), "inner")

  // Join is a WIDE transformations, will involve SHUFFLE operation

  // Finish DSEx01

}

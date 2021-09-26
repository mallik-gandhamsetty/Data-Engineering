package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DFLecture08 extends App{

  /**
   * What we cover in this app:
   * - Joins
   *
   * -- check this link to understand jointypes https://riptutorial.com/sql/example/22934/join-terminology--inner--outer--semi--anti---
   *
   */

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("Joins")
    .config("spark.master", "local")
    .getOrCreate()

  val guitarsDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/guitars.json")

  val guitaristsDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/guitarPlayers.json")

  val bandsDF = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/data/bands.json")

  // Joining two dataframes by specifying a join condition
  // Syntax:
  //      df1.join(df2, join_condition, join_type)
  // join_condition: df1.column1 === df2.column1
  // join type: inner, left_outer, right_outer, outer, left_semi, right_semi


  // INNER JOIN
  // save the result to new varaiable
  val guitaristsBandsJoinDF = guitaristsDF.join(bandsDF, guitaristsDF.col("band") === bandsDF.col("id"), "inner")

  // print and observe the column names
  guitaristsBandsJoinDF.show

  // the below command fails because id column is duplicated (even name is twice) and
  // spark doesn't know which column you are referring to
  // beware of similar column names after a join operation
  // guitaristsBandsDF.select("id", "band").show

  // you can also store the join condition in a variable
  val joinCondition = guitaristsDF.col("band") === bandsDF.col("id")

  // use the above join condition to perform an inner join
  guitaristsDF.join(bandsDF, joinCondition, "inner").show

  // outer joins
  // left outer = Combines left and right rows that match, and includes non-matching left rows.
  guitaristsDF.join(bandsDF, joinCondition, "left_outer").show

  // right outer = Combines left and right rows that match, and includes non-matching right rows.
  guitaristsDF.join(bandsDF, joinCondition, "right_outer").show

  // full outer = Union of left and right outer join.
  guitaristsDF.join(bandsDF, joinCondition, "outer").show

  // left semi join = Includes left rows that match right rows.
  guitaristsDF.join(bandsDF, joinCondition, "left_semi").show

  // right semi joins = Includes left rows that match right rows.
  guitaristsDF.join(bandsDF, joinCondition, "right_semi").show

  // left anti-joins = Includes left rows that do not match right rows.
  guitaristsDF.join(bandsDF, joinCondition, "left_anti").show

  // right anti-joins = Includes right rows that do not match left rows.
  guitaristsDF.join(bandsDF, joinCondition, "right_anti").show


  // How to solve the similar column name problem when joining two or more dataframes
  // option 1 - rename the column on which we are joining
  guitaristsDF.join(bandsDF.withColumnRenamed("id", "band"), "band")

  // option 2 - drop the duplicate column
  guitaristsBandsJoinDF.drop(bandsDF.col("id"))

  // option 3 - rename the column causing problem and keep the data
  val newBandsDF = bandsDF.withColumnRenamed("id", "bandId")
  guitaristsDF.join(newBandsDF, guitaristsDF.col("band") === newBandsDF.col("bandId"))

  // using complex types
  guitaristsDF.join(guitarsDF.withColumnRenamed("id", "guitarId"), expr("array_contains(guitars, guitarId)"))

  // Finish DFEx06


}

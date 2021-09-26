package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, expr, when}

object DFLecture09 extends App {

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DataFrames - When Otherwise")
    .config("spark.master", "local")
    .getOrCreate()

  import spark.implicits._

  val data = List(("James", "", "Smith", "36636", "M", 60000),
    ("Michael", "Rose", "", "40288", "M", 70000),
    ("Robert", "", "Williams", "42114", "", 400000),
    ("Maria", "Anne", "Jones", "39192", "F", 500000),
    ("Jen", "Mary", "Brown", "", "F", 0))

  val cols = Seq("first_name", "middle_name", "last_name", "dob", "gender", "salary")
  val df = spark.createDataFrame(data).toDF(cols: _*)

  df.show

  // 1. Using “when otherwise” on Spark DataFrame.
  // when can be used in select as well.
  val df2 = df.withColumn("new_gender", when(col("gender") === "M", "Male")
    .when(col("gender") === "F", "Female")
    .otherwise("Unknown"))

  df2.show()

  // using expr
  val df3 = df.withColumn("new_gender",
    expr(
      """case when gender = 'M' then 'Male'
      when gender = 'F' then 'Female'
      else 'Unknown' end"""))

  // use in select
  val df4 = df.select(col("*"),
    expr("case when gender = 'M' then 'Male' " +
      "when gender = 'F' then 'Female' " +
      "else 'Unknown' end").alias("new_gender"))


  // when..otherwise with logical operators ||, &&
  val dataDF = Seq(
    (66, "a", "4"), (67, "a", "0"), (70, "b", "4"), (71, "d", "4"
    )).toDF("id", "code", "amt")

  dataDF.show()

  dataDF.withColumn("new_column",
    when(col("code") === "a" || col("code") === "d", "A")
      .when(col("code") === "b" && col("amt") === "4", "B")
      .otherwise("A1"))
    .show()


}

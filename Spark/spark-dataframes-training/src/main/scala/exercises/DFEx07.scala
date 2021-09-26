package exercises

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType, StructField, StringType}

object DFEx07 extends App{

  // Prepare users.dat:
  // Load a double delimited csv file into a Dataframe by specifying the schema programmatically

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("DataFrames Solved Exercise")
    .config("spark.master", "local")
    .getOrCreate()

  // create a dataset
  val usersDS = spark.read.textFile("src/main/resources/data/users.dat")

  // Method 1: using case class
  import spark.implicits._

  case class User(UserID: String, Gender: String, Age: String, Occupation: String, ZipCode: String)

  val userDF = usersDS.map(_.split("::")).map(x => User(x(0), x(1), x(2), x(3), x(4))).toDF

  userDF.show

  // using tuples
  val userDF2 = usersDS.map(_.split("::")).map(x => (x(0), x(1), x(2), x(3), x(4))).toDF

  userDF2.show

  // using RDD and schema - low level

  // Define a string containing column names
  val schemaString = "UserID Gender Age Occupation Zip-code"

  // Lets try to automate creation of schema to some degree using map
  val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))

  // create RDD[Row]
  val rowRDD = usersDS.rdd.map(_.split("::")).map(x => Row(x(0), x(1), x(2), x(3), x(4)))

  val rowDF = spark.createDataFrame(rowRDD, schema)

  rowDF.show()

  // instead of row type, you can make use of User case class for Schema. Once RDD[User] is created, using implicts toDF will convert it to DF
  val userDF3 = usersDS.rdd.map(_.split("::")).map(x => User(x(0), x(1), x(2), x(3), x(4))).toDF

  userDF3.show

}

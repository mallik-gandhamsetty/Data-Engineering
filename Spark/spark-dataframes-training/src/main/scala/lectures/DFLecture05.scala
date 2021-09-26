package lectures

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

object DFLecture05 extends App{


  /**
   * What we cover in this app:
   * - Reading from JSON files
   * - Reading from CSV files
   * - Reading from text files
   *
   * - Writing a DF as Json
   * - Writing a DF as CSV
   * - Writing a DF as Parquet
   *
   */

  // creating a SparkSession
  val spark = SparkSession.builder()
    .appName("Data Sources and Formats")
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

  /*
      Reading a DF:
      - format
      - schema or inferSchema = true
      - path
      - zero or more options
     */
  val carsDF = spark.read
    .format("json")
    .schema(carsSchema)                   // enforce a schema
    .option("mode", "failFast")           // other modes exist. see below comment
    .option("path", "src/main/resources/data/cars.json")
    .load()
  /*
    mode (default PERMISSIVE): allows a mode for dealing with corrupt records during parsing.
    PERMISSIVE : sets other fields to null when it meets a corrupted record, and puts the malformed string into a new field configured by columnNameOfCorruptRecord. When a schema is set by user, it sets null for extra fields.
    DROPMALFORMED : ignores the whole corrupted records.
    FAILFAST : throws an exception when it meets corrupted records.
  */


  // alternative reading with options map
  // instead of using .option multiple times, use Map of option
  val carsDFWithOptionMap = spark.read
    .format("json")
    .options(Map(
      "mode" -> "failFast",
      "path" -> "src/main/resources/data/cars.json",
      "inferSchema" -> "true"
    ))
    .load()


  /*
   Writing DFs
   - format
   - save mode = overwrite, append, ignore, errorIfExists
   - path
   - zero or more options
  */
  carsDF.write
    .format("json")
    .mode(SaveMode.Overwrite)               // write mode can be a String like "overwrite" or "append"
    .save("src/main/resources/data/cars_dupe.json")


  // write in Parquet format
  carsDF.write
    .mode(SaveMode.Overwrite)
    .save("src/main/resources/data/cars.parquet")


  // optional things you can do while reading from a JSON file source
  val jsonCarDF = spark.read
    .schema(carsSchema)
    .option("dateFormat", "YYYY-MM-dd") // if Spark fails to parse data using this format , it will put null
    .option("allowSingleQuotes", "true") // if your json contains single quote instead of double quote
    .option("compression", "uncompressed") // if your file is compressed - bzip2, gzip, lz4, snappy, deflate
    .json("src/main/resources/data/cars.json")


  // Reading from CSV files
  // Let's read another file to demonstrate options you can specify for reading CSV files
  // open stocks.csv from data folder and have a look at the data once
  // first, lets define a schema
  val stocksSchema = StructType(Array(
    StructField("symbol", StringType),
    StructField("date", DateType),
    StructField("price", DoubleType)
  ))

  val stockDF =   spark.read
    .schema(stocksSchema)
    .option("dateFormat", "MMM dd YYYY")
    .option("header", "true")
    .option("sep", ",")
    .option("nullValue", "")
    .csv("src/main/resources/data/stocks.csv")

  // mini exercise: define above DF using Option Map instead of separate options




  // Reading Text files
  spark.read.text("src/main/resources/data/sampleTextFile.txt").show()


  // Reading from a database
  // To read from a Database, you need the URL of the database server, User, password and the driver to use depending on database
  // Here is an exmaple of how you would connect to a Postgres DB

  //  val driver = "org.postgresql.Driver"
  //  val url = "jdbc:postgresql://localhost:5432/rtjvm"
  //  val user = "user"
  //  val password = "password"
  //
  //  val employeesDF = spark.read
  //    .format("jdbc")
  //    .option("driver", driver)
  //    .option("url", url)
  //    .option("user", user)
  //    .option("password", password)
  //    .option("dbtable", "public.employees")
  //    .load()


  // Finish the exercise DFEx03 under exercises package

}

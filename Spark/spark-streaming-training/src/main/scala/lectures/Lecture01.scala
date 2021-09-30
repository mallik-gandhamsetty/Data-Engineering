package lectures

import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.SparkSession

object Lecture01 extends App {

  val spark = SparkSession.builder()
    .appName("Sensor Streaming App")
    .master("local[2]")
    .getOrCreate()

  import spark.implicits._

  // Before we can load data from sensordata.csv, we will create a schema for the csv file
  // so that we can process the csv file directly into a dataframe:
  val sensorSchema = new StructType()
    .add("resid", "string")
    .add("date", "string")
    .add("time", "string")
    .add("hz", "double")
    .add("disp", "double")
    .add("flow", "double")
    .add("sedPPM", "double")
    .add("psi", "double")
    .add("chlppm", "double")

  // create an input stream
  val sensorCsvDF = spark.readStream
    .option("sep", ",")
    .schema(sensorSchema)
    .csv("src/main/resources/data/sensor_stream/")

  println(sensorCsvDF.isStreaming)

  // filter sensor data for low psi
  val filterSensorDF = sensorCsvDF.filter("psi < 5.0")

  // use the writeStream.format("console").start() method to display the contents of the stream on screen:
  val query = filterSensorDF.writeStream
    .option("path", "src/main/resources/data/alert_output/")
    .option("checkpointLocation", "src/main/resources/data/checkpoint")
    .format("csv")
    .start()

  // Wait for the computation to terminate
  query.awaitTermination()
}

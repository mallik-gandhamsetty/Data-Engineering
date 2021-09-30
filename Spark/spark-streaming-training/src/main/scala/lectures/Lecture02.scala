package lectures

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

object Lecture02 extends App {

  // Create SparkSession
  val spark = SparkSession.builder()
    .appName("Sensor Streaming App")
    .master("local[2]")
    .getOrCreate()

  // Import package spark.implicits
  import spark.implicits._

  // Schema for sensor data
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

  // Load pump and maint data
  val vendorDF = spark.read.option("inferSchema", "true")
    .csv("src/main/resources/data/sensorvendor.csv")
    .toDF("resid", "pumpType", "purchaseDate", "serviceDate", "vendor", "longitude", "lattitude")

  println("pumpDF take 5")
  vendorDF.show(5)

  val maintDF = spark.read.option("inferSchema", "true")
    .csv("src/main/resources/data/sensormaint.csv")
    .toDF("resid", "eventDate", "technician", "description")
  println("maintDF take 5")
  maintDF.show(5)

  // create views for maint and pump Datasets
  maintDF.createTempView("maint")
  vendorDF.createTempView("vendor")

  // parse the lines of data into sensor objects
  val sensorDF = spark.readStream.option("sep", ",").schema(sensorSchema).csv("src/main/resources/data/sensor_stream/")
  sensorDF.createTempView("sensor")

  // compute Sensor max, min and average by date
  val res = spark.sql(
    """SELECT resid, date, MAX(hz) as maxhz, min(hz) as minhz,
      avg(hz) as avghz, MAX(disp) as maxdisp, min(disp) as mindisp,
      avg(disp) as avgdisp, MAX(flow) as maxflow, min(flow) as minflow,
      avg(flow) as avgflow,MAX(sedPPM) as maxsedPPM, min(sedPPM) as minsedPPM,
      avg(sedPPM) as avgsedPPM, MAX(psi) as maxpsi, min(psi) as minpsi,
      avg(psi) as avgpsi, MAX(chlPPM) as maxchlPPM, min(chlPPM) as minchlPPM,
      avg(chlPPM) as avgchlPPM
      FROM sensor
      GROUP BY resid, date""")

  println("Sensor max, min and averages")

  // start computation
  val dataAvgStream = res.writeStream.outputMode("complete").format("console").start()

  // Filter sensor data for low psi
  val filterSensorDF = sensorDF.filter("psi < 5.0")
  filterSensorDF.createTempView("alert")
  println("low pressure alert")

  val filterStream = filterSensorDF.writeStream.outputMode("append").format("console").start()

  // alert stream
  val alertSensorMaintDF = spark.sql(
    """select s.resid, s.date, s.psi, p.pumpType, p.purchaseDate, p.serviceDate, p.vendor,
      m.eventDate, m.technician, m.description
      from sensor s
      join pump p
        on s.resid = p.resid
      join maint m
        on p.resid = m.resid
      """)

  println("alert sensor maintenance data")
  val alertSensorMaintStream = alertSensorMaintDF.writeStream.format("console").start() // Default output mode is Append

  // Wait for the computation to terminate
  filterStream.awaitTermination()
  dataAvgStream.awaitTermination()
  alertSensorMaintStream.awaitTermination()

}

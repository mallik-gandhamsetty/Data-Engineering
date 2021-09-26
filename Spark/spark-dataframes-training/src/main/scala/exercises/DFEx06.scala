package exercises

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DFEx06 extends App{

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExamples.com")
    .getOrCreate()


  val emp = Seq((1,"Smith",-1,"2018","10","M",3000),
    (2,"Rose",1,"2010","20","M",4000),
    (3,"Williams",1,"2010","10","M",1000),
    (4,"Jones",2,"2005","10","F",2000),
    (5,"Brown",2,"2010","40","",-1),
    (6,"Brown",2,"2010","50","",-1)
  )
  val empColumns = Seq("emp_id","name","superior_emp_id","year_joined","emp_dept_id","gender","salary")

  import spark.sqlContext.implicits._
  val empDF = emp.toDF(empColumns:_*)

  empDF.show(false)

  val dept = Seq(("Finance",10),
    ("Marketing",20),
    ("Sales",30),
    ("IT",40)
  )

  val deptColumns = Seq("dept_name","dept_id")
  val deptDF = dept.toDF(deptColumns:_*)

  deptDF.show(false)


  /**
   * Exercises:
   *  1. Understand the data and attributes and
   *  2. perform various joins - inner, outer, left outer, full outer, right outer, left semi, right semi, left anti and right anti
   */

  // inner join



  // left outer



  // self join to get employee and superior employee details

  empDF.as("emp1").join(empDF.as("emp2"),
    col("emp1.superior_emp_id") === col("emp2.emp_id"),"inner")
    .select(col("emp1.emp_id"),col("emp1.name"),
      col("emp2.emp_id").as("superior_emp_id"),
      col("emp2.name").as("superior_emp_name"))
    .show(false)

}

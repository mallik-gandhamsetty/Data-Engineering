name := "spark-streaming-training"

version := "0.1"

scalaVersion := "2.12.10"
val sparkVersion = "3.1.2"

resolvers ++= Seq(
  "MavenRepository" at "https://mvnrepository.com"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)
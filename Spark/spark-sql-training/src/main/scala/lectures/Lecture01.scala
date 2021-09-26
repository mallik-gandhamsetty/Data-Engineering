package lectures

import org.apache.spark.sql.SparkSession


object Lecture01 extends App{

  val spark = SparkSession.builder()
    .appName("Lecture01 - Spark SQL")
    .config("spark.master", "local")
    .config("spark.sql.warehouse.dir", "src/main/resources/warehouse")   // setup a local metadata db to register spark tables
    .getOrCreate()

  // 1. Look at Movies.dat, Users.dat and Ratings.dat files and observe the file headers, delimiter etc

  // 2. Read the file as a dataFrame and create a temp view
  spark.read.textFile("src/main/resources/data/in/movies.dat").createOrReplaceTempView("movies_staging")
  spark.read.textFile("src/main/resources/data/in/ratings.dat").createOrReplaceTempView("ratings_staging")
  spark.read.textFile("src/main/resources/data/in/users.dat").createOrReplaceTempView("users_staging")

  // Create a database to store the tables
  spark.sql("drop database if exists sparkdatalake cascade")
  spark.sql("create database sparkdatalake")

  // explore data
  spark.sql("Select * from movies_staging limit 5").show
  spark.sql("Select * from ratings_staging limit 5").show
  spark.sql("Select * from users_staging limit 5").show

  // Transform the dataframes into proper tables
  // movies
  spark.sql(""" Select
      split(value,'::')[0] as movieid,
      split(value,'::')[1] as title,
      substring(split(value,'::')[1], length(split(value,'::')[1])-4, 4) as year,
      split(value,'::')[2] as genre
      From movies_staging""")
    .write
    .mode("overwrite")
    .saveAsTable("sparkdatalake.movies")

  // users
  spark.sql(""" Select
      split(value,'::')[0] as userid,
      split(value,'::')[1] as gender,
      split(value,'::')[2] as age,
      split(value,'::')[3] as occupation,
      split(value,'::')[4] as zipcode
      from users_staging """)
    .write.mode("overwrite")
    .saveAsTable("sparkdatalake.users")

  // ratings
  spark.sql(""" Select
      split(value,'::')[0] as userid,
      split(value,'::')[1] as movieid,
      split(value,'::')[2] as rating,
      split(value,'::')[3] as timestamp
      from ratings_staging """)
    .write.mode("overwrite")
    .saveAsTable("sparkdatalake.ratings")

  // explore the data after transformation
  spark.sql("Select * from sparkdatalake.movies limit 5").show
  spark.sql("Select * from sparkdatalake.ratings limit 5").show
  spark.sql("Select * from sparkdatalake.users limit 5").show


  // Analysis
  // 1. What is the average rating for each movie? save the result as a file
  spark.sql("""Select movieid,
      cast((avg(rating)) as decimal(16,2)) as Average_rating
      from sparkdatalake.ratings
      group by movieid
      order by cast(movieid as int) asc
      """)
    .repartition(1)  // try without this option. spark will create multiple files if you don't specify this
    .write.format("csv")
    .option("header","true")
    .save("src/main/resources/out/average_rating_per_movie.csv")


  // Find the list of oldest released movies
  spark.sql("""Select * from sparkdatalake.movies m1
      where m1.year = (Select min(m2.year) from sparkdatalake.movies m2)""").show


  // Find the number of movies released each year
  spark.sql("""Select distinct(year), count(movieid) as Number_of_Movies
      from sparkdatalake.movies
      group by year""").show


  // Find the number of movies for each rating
  // Instead of specifying the database each time, you can set the context to use a particular database
  spark.sql("use sparkdatalake")

  spark.sql("""Select rating as Ratings, count(rating) as Number_of_Movies_per_rating
      from sparkdatalake.ratings
      group by rating
      order by rating asc""").show


  // How many users rated each movie?
  spark.sql("""Select movieid,
    count(userid) as Total_number_of_Users,
    from sparkdatalake.ratings
    group by movieid
    order by cast(movieid as int) asc
    """).show


  // What is the total rating For each movie?
  spark.sql("""Select movieid,
    sum(rating) as Total_rating,
    from sparkdatalake.ratings
    group by movieid
    order by cast(movieid as int) asc
    """)

}

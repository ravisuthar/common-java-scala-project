/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package com.example.scala

import com.example.java.AppJava
import org.apache.spark.sql.{DataFrame, SparkSession}

object AppScala {
  def main(args: Array[String]): Unit = {
    println(greeting())

    //calling java method from scala
    AppJava.main(args)

    //calling mongoDB
    mongoDBSpark()
  }

  def greeting(): String = "Hello, world! from Scala"

  def mongoDBSpark(): Unit = {

    var spark = SparkSession
      .builder
      .appName("Spark MongoDB")
      .master("local[*]")
      .config("spark.jars.packages", "org.mongodb.spark:mongo-spark-connector_2.13:10.2.0")
      .config("spark.mongodb.read.connection.uri", "mongodb://localhost:27017/latest_db.students")
      .config("spark.mongodb.write.connection.uri", "mongodb://localhost:27017/latest_db.students")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val mongodf: DataFrame = spark.read.format("mongodb").load()

    mongodf.printSchema()
    mongodf.show()
    //mongodf.createOrReplaceTempView("tempMongo")
    //spark.sql("SELECT * FROM tempMongo").show()


    //write data from json to mongodb
    val df = spark.read.format("json").load("app/src/main/resources/example.json")
    df.write.format("mongodb").mode("overwrite").save()

    //read data
    val mongodf2: DataFrame = spark.read.format("mongodb").load()
    mongodf2.show()


    spark.stop()
    println("completed")
  }


  def getSparkSession(): SparkSession =
    SparkSession
      .builder
      .appName("Spark MongoDB")
      .master("local[*]")
      .config("spark.jars.packages", "org.mongodb.spark:mongo-spark-connector_2.13:10.2.0")
      .config("spark.mongodb.read.connection.uri", "mongodb://localhost:27017/latest_db.students")
      .config("spark.mongodb.write.connection.uri", "mongodb://localhost:27017/latest_db.students")
      .getOrCreate()

}

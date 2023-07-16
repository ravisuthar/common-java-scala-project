package com.example.java;

import com.example.scala.AppScala;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class AppJava {
    public static void main(String[] args) {
        System.out.println("Hello World!!  from Java");

        //calling scala code from Java
        // AppScala.main(args);

        //calling Scala MongoDB Spark method
        //AppScala.mongoDBSpark();

        SparkSession sparkSession = AppScala.getSparkSession();
        sparkSession.sparkContext().setLogLevel("ERROR");

        DataFrameReader read = sparkSession.read();
        Dataset<Row> mongodb = read.format("mongodb").load();
        mongodb.printSchema();
        mongodb.show();
    }
}

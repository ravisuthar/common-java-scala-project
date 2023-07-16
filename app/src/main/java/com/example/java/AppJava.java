package com.example.java;

import com.example.scala.AppScala;

public class AppJava {
    public static void main(String[] args) {
        System.out.println("Hello World!!  from Java");

        //calling scala code from Java
        // AppScala.main(args);

        //calling Scala MongoDB Spark method
        AppScala.mongoDBSpark();
    }
}

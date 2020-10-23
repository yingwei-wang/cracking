package com.yingwei.cracking.spark.invertedIndex

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.sql.functions.{callUDF, input_file_name}

object DocumentReader {

  case class Document(
      id: String,
      line: String)

  def readAsDf(path: String)(implicit spark: SparkSession): DataFrame = {
    spark.udf.register("get_file_name", (path: String) => path.split("/").last.split("\\.").head)
    spark.read
      .text(path)
      .withColumn("id", callUDF("get_file_name", input_file_name()))
      .withColumnRenamed("value", "line")
  }

  def readAsRdd(path: String)(implicit spark: SparkSession): RDD[(String, String)] = {
    import spark.implicits._
    readAsDf(path).select("id", "line").as[(String, String)].rdd
  }

  def readAsDs(path: String)(implicit spark: SparkSession): Dataset[Document] = {
    import spark.implicits._
    readAsDf(path).as[Document]
  }

}

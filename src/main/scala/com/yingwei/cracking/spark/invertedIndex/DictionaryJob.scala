package com.yingwei.cracking.spark.invertedIndex

import com.yingwei.cracking.spark.invertedIndex.DocumentReader.Document
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object DictionaryJob {

  def build(path: String)(implicit spark: SparkSession): List[(String, Int)] = {
    //Dataframe
    val documentDF = DocumentReader.readAsDf(path)
    buildDictionaryWithDf(documentDF)

    //Dateset
    //val documentDs = readAsDs(path)
    //buildDictionaryWithDs(documentDs)

    //RDD
    //val dRdd = readAsRdd(path)
    //buildDictionaryWithRdd(dRdd)

  }

  def buildDictionaryWithDf(df: DataFrame)(implicit spark: SparkSession): List[(String, Int)] = {
    import spark.implicits._
    //solution 1
    //val dd = df.select(explode(split($"line", " "))).distinct()

    //solution 2
    val dd = df.flatMap(row => row.getAs[String]("line").split(" ")).toDF().distinct()

    dd.collect().map(_.getString(0)).zipWithIndex.toList
  }

  def buildDictionaryWithDs(ds: Dataset[Document])(implicit spark: SparkSession): List[(String, Int)] = {
    import spark.implicits._
    //solution 1
    val dd = ds.select(explode(split('line, " "))).as[String].distinct()

    //solution 2
//    val dd = ds.flatMap(_.line.split(" ")).distinct()

    dd.collect().zipWithIndex.toList
  }

  def buildDictionaryWithRdd(rdd: RDD[(String, String)])(implicit spark: SparkSession): List[(String, Int)] = {

    val dd = rdd
      .flatMap {
        case (_, line) =>
          if (line.isEmpty) None else line.split("\\s+")
      }
      .distinct()

    dd.collect().zipWithIndex.toList
  }

}

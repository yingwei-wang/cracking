package com.yingwei.cracking.spark.invertedIndex

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object IndexJob {

  def buildIndexWithDf(path: String)(implicit spark: SparkSession): DataFrame = {

    import spark.implicits._

    val dictionary = DictionaryJob.build(path).toMap

    val broadcastDic = spark.sparkContext.broadcast(dictionary)

    val dicCol = typedLit(broadcastDic.value)

    val documentDF = DocumentReader.readAsDf(path)

    documentDF
      .select(explode(split($"line", "\\s+")).as("word"), $"id")
      .groupBy($"word")
      .agg(array_sort(collect_set("id")))
      .withColumn("wordId", coalesce(dicCol($"word")))
      .drop("word")
      .sort($"wordId")

  }

  def buildIndexWithRdd(path: String)(implicit spark: SparkSession): RDD[(Int, List[String])] = {

    val dictionary = DictionaryJob.build(path).toMap

    val broadcastDic = spark.sparkContext.broadcast(dictionary)

    val documentRdd = DocumentReader.readAsRdd(path)

    documentRdd
      .flatMap {
        case (id, line) =>
          val words = line.split("\\s+")
          words.map { w =>
            val wid = broadcastDic.value(w)
            (wid, id)
          }
      }
      .groupByKey()
      .mapValues(_.toSet.toList.sorted)
      .sortByKey()
  }

}

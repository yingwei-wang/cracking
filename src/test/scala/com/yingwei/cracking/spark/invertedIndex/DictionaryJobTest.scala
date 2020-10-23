package com.yingwei.cracking.spark.invertedIndex

import com.yingwei.cracking.spark.test.SharedSparkSession
import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

class DictionaryJobTest extends FunSuite with SharedSparkSession{

  implicit lazy val spark: SparkSession = ss

  val path = "file:///Users/yingwei_wang/Workspace/yw-projects/cracking/dataset*"

  test("DF solution"){
    val ret = DictionaryJob.build(path)

    ret.foreach{ row =>
      println(row)
    }

  }




}

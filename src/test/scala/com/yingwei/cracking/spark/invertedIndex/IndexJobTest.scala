package com.yingwei.cracking.spark.invertedIndex

import com.yingwei.cracking.spark.test.SharedSparkSession
import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite

class IndexJobTest extends FunSuite with SharedSparkSession{

  implicit lazy val spark: SparkSession = ss

  val path = "file:///Users/yingwei_wang/Workspace/yw-projects/cracking/dataset*"

  test("DF solution"){

    val dd = IndexJob.buildIndexWithDf(path)

    dd.show(false)

  }

  test("RDD solution"){

    val dd = IndexJob.buildIndexWithRdd(path)

    dd.collect().foreach(println)

  }

}

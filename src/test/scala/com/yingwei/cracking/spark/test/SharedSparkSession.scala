package com.yingwei.cracking.spark.test

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSparkSession extends BeforeAndAfterAll {
  this: Suite =>

  private val SPARK_TEST_PARALLELISM: String = "2"

  @transient private var _ss: SparkSession = _

  def ss: SparkSession = _ss

  def sparkConf: SparkConf =
    new SparkConf()
      .set("spark.ui.enabled", "false")
      .set("spark.sql.shuffle.partitions", SPARK_TEST_PARALLELISM)
      .set("spark.default.parallelism", SPARK_TEST_PARALLELISM)
      .set("spark.driver.host", "localhost")

  override def beforeAll(): Unit = {
    System.setProperty("hadoop.home.dir", "/")
    _ss = SparkSession
      .builder()
      .appName(this.getClass.getSimpleName)
      .master(s"local[$SPARK_TEST_PARALLELISM]")
      .config(sparkConf)
      .getOrCreate()
    super.beforeAll()
  }

  override def afterAll(): Unit = {
    try {
      if (_ss != null) {
        _ss.stop()
        _ss = null
      }
      // To avoid Akka rebinding to the same port, since it doesn't unbind immediately on shutdown
      System.clearProperty("spark.driver.port")
    } finally {
      super.afterAll()
    }
  }
}

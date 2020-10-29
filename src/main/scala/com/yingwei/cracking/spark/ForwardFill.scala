package com.yingwei.cracking.spark

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object ForwardFill {

  /**
    * https://johnpaton.net/posts/forward-fill-spark/
    *
    * Input
    * +-------------------+--------+------------------+
    * |               time|location|       temperature|
    * +-------------------+--------+------------------+
    * |2017-09-09 12:00:00|   shade|18.830184076113213|
    * |2017-09-09 12:00:00|     sun|              null|
    * |2017-09-09 12:30:00|   shade|              null|
    * |2017-09-09 12:30:00|     sun| 21.55237663805009|
    * |2017-09-09 13:00:00|   shade| 18.59059750682235|
    * |2017-09-09 13:00:00|     sun|              null|
    * |2017-09-09 13:30:00|   shade|              null|
    * |2017-09-09 13:30:00|     sun|22.587784977960474|
    * |2017-09-09 14:00:00|   shade|19.101003724324197|
    * |2017-09-09 14:00:00|     sun|20.548896316341516|
    * +-------------------+--------+------------------+
    */

  def forwardFill(df: DataFrame)(implicit spark: SparkSession): DataFrame = {

    import spark.implicits._

    //val window = Window.partitionBy("location").orderBy("time").rowsBetween(Window.unboundedPreceding, -1)
    //df.withColumn("filled", coalesce($"temperature", last($"temperature", ignoreNulls = true).over(window)))

    val window = Window.partitionBy("location").orderBy("time").rowsBetween(Window.unboundedPreceding, 0)
    df.withColumn("filled", last($"temperature", ignoreNulls = true).over(window))
  }

}

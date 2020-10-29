package com.yingwei.cracking.spark

import com.yingwei.cracking.spark.test.SharedSparkSession
import com.yingwei.cracking.spark.test.SparkSessionExt.SparkSessionExt.SparkSessionMethods
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.DataTypes
import org.scalatest.FunSuite

class ForwardFillTest extends FunSuite with SharedSparkSession {

  implicit lazy val spark: SparkSession = ss

  test("forward fill") {

    val df = spark.createDF(
      List(
        ("2017-09-09", "shade", 18.83018407),
        ("2017-09-09", "sun", null),
        ("2017-09-10", "shade", null),
        ("2017-09-10", "sun", 21.552312),
        ("2017-09-11", "shade", null),
        ("2017-09-11", "sun", null),
        ("2017-09-12", "shade", 19.10100372),
        ("2017-09-12", "sun", null)
      ),
      List(
        ("time", DataTypes.StringType, true),
        ("location", DataTypes.StringType, true),
        ("temperature", DataTypes.DoubleType, true)
      )
    )

    df.show()

    val outDF = ForwardFill.forwardFill(df)
    outDF.sort("time", "location").show()

  }

}

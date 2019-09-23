package cn.bmsoft.bigdata.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

/**
  * Created by yml on 2019/9/23.
  */
object Register {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WC")
    val spark: SparkSession = SparkSession.builder().appName("sparksql example").config(conf).getOrCreate()
    import spark.implicits._
    val df: DataFrame = spark.read.json("in/people.json")
    spark.udf.register("addName",(x:String)=>"Name:"+x)
    df.createOrReplaceTempView("people")
    spark.sql("select addName(name),age from people").show()
  }
}

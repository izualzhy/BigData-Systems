package cn.izualzhy

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

import scala.collection.JavaConverters._


/**
 * Author: yingshin
 * Date: 2022/3/8 11:18
 * Package: cn.izualzhy
 * Description:
 *
 */
object JustTest extends App {
  val env = StreamExecutionEnvironment.getExecutionEnvironment
  env.setParallelism(1)
  env.enableCheckpointing(60000)
  val tEnv = StreamTableEnvironment.create(env)

  val source = env.fromElements(
    //    Row.of("2017-11-26 01:00:00"),
    //    Row.of("2017-11-26 01:01:00"),
    //    Row.of("2017-11-26 02:03:00"),
    //    Row.of("2017-11-26 01:02:03")
    "2017-11-26 01:00:00Z",
    "2017-11-26 01:01:00Z",
    "2017-11-26 02:03:00Z",
    "2017-11-26 01:02:03Z"
  )
  val source_table = tEnv.fromDataStream(source).as("a")

  tEnv.createTemporaryView(
    "source_table",
    source_table)
  tEnv.from("source_table").printSchema()

  tEnv.executeSql("""SELECT DATE_FORMAT(a, 'yyyy-MM-dd HH:00') FROM source_table""")
    .collect()
    .asScala
    .foreach(println)
}

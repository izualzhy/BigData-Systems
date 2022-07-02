package cn.izualzhy

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

import scala.collection.JavaConverters._


/**
 * Author: yingshin
 * Date: 2022/4/7 18:59
 * Package: cn.izualzhy
 * Description:
 *
 */
object ParquetReadTest extends App {
  val env = StreamExecutionEnvironment.getExecutionEnvironment
  env.setParallelism(1)
  env.enableCheckpointing(60000)
  val tEnv = StreamTableEnvironment.create(env)

  tEnv.executeSql(
    """
      |CREATE TABLE test_parquet_table (
      | hive_item_key STRING,
      | hive_item_version STRING,
      | hive_item_valid INT,
      | user_id STRING,
      | job STRING,
      | age INT,
      | name STRING,
      | `date` STRING
      |) PARTITIONED BY (`date`) WITH (
      | 'connector' = 'filesystem',
      | 'path' = '/Users/zhangying/Downloads/delta_test/0407/tbl_sql_merge_sink',
      | 'format' = 'parquet'
      |)""".stripMargin)

  tEnv.executeSql("""SELECT * FROM test_parquet_table""")
    .collect().asScala.foreach(println)

}

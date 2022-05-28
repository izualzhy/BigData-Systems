package cn.izualzhy

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment

object LiteralInRowTest extends App {
  val env = StreamExecutionEnvironment.getExecutionEnvironment
  val tEnv = StreamTableEnvironment.create(env)

  val source = env.fromElements("a")
  val source_table = tEnv.fromDataStream(source).as("a")

  tEnv.createTemporaryView(
    "source_table",
    source_table)
  val sql = "SELECT Row('literal_column', a) FROM source_table"
  /*
  Exception in thread "main" org.apache.flink.table.api.SqlParserException: SQL parse failed. Encountered "\'literal_column\'" at line 1, column 15.
   */
//  val error_sql = "SELECT Row(a, 'literal_column') FROM source_table"
  tEnv.executeSql(sql).print()

}

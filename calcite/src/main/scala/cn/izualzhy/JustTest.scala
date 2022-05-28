package cn.izualzhy

import org.apache.calcite.util.BuiltInMethod
import org.codehaus.commons.compiler.{CompilerFactoryFactory, IExpressionEvaluator}

object JustTest extends App {
  val sql = "SELECT Row('literal_column', a) FROM source_table"
}

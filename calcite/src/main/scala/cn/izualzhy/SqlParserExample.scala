package cn.izualzhy

import org.apache.calcite.sql.{SqlKind, SqlSelect}
import org.apache.calcite.sql.parser.SqlParser

/**
 * Author: yingshin
 * Date: 2022/4/12 11:48
 * Package: cn.izualzhy
 * Description:
 *
 */
object SqlParserExample extends App {
  val sql = "SELECT * FROM emps WHERE id = 1"
  val sqlParser = SqlParser.create(sql)

  val sqlNode = sqlParser.parseQuery()


  sqlNode.getKind match {
    case SqlKind.SELECT => println(s"from:${sqlNode.asInstanceOf[SqlSelect].getFrom}")
    case _ => println("to be continued.")
  }
}

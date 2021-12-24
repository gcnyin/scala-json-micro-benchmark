package example

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.openjdk.jmh.annotations.Benchmark
import upickle.default._

class HelloWorld {
  @Benchmark
  def circeSerialization(): Unit = {
    User("Zhang san", 23, "Beijing, China", "China").asJson.noSpaces
  }

  @Benchmark
  def upickleSerialization(): Unit = {
    write(User("Zhang san", 23, "Beijing, China", "China"))
  }

  @Benchmark
  def circeParse(): Unit = {
    val json = """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China"}"""
    decode[User](json)
  }

  @Benchmark
  def upickleParse(): Unit = {
    val json = """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China"}"""
    read[User](json)
  }
}

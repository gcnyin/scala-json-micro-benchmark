package example

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import upickle.default._

@State(Scope.Benchmark)
class HelloWorld {
  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  val user: User = User("Zhang san", 23, "Beijing, China", "China")

  val json = """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China"}"""

  @Benchmark
  def circeSerialization(): Unit = {
    user.asJson.noSpaces
  }

  @Benchmark
  def upickleSerialization(): Unit = {
    write(user)
  }

  @Benchmark
  def jacksonSerialization(): Unit = {
    mapper.writeValueAsString(user)
  }

  @Benchmark
  def circeParse(): Unit = {
    decode[User](json)
  }

  @Benchmark
  def upickleParse(): Unit = {
    read[User](json)
  }

  @Benchmark
  def jacksonParse(): Unit = {
    mapper.readValue(json, new TypeReference[User] {})
  }
}

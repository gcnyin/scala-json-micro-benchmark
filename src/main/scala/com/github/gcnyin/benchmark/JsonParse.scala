package com.github.gcnyin.benchmark

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.github.gcnyin.benchmark.models.User
import io.circe.generic.auto._
import io.circe.parser.decode
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import upickle.default.{read, readBinary, writeBinary}
import org.json4s.NoTypeHints
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read => jRead}
import play.api.libs.json.Json

@State(Scope.Benchmark)
class JsonParse {
  implicit val formats = Serialization.formats(NoTypeHints)

  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  val json =
    """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China","base":"Shanghai","salary":1234.56}"""

  val upickleJsonString =
    """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China","base":["Shanghai"],"salary":1234.56,"favoriteFruit":[]}"""

  val user: User = User("Zhang san", 23, "Beijing, China", "China", Option("Shanghai"), 1234.56, Option.empty)

  val upickleMessagePackValue = writeBinary(user)

  @Benchmark
  def circe(): Unit = {
    decode[User](json)
  }

  @Benchmark
  def upickleJson(): Unit = {
    read[User](upickleJsonString)
  }

  /**
    * not json, for reference only
    */
  @Benchmark
  def upickleMessagePack(): Unit = {
    readBinary[User](upickleMessagePackValue)
  }

  @Benchmark
  def jackson(): Unit = {
    mapper.readValue(json, new TypeReference[User] {})
  }

  @Benchmark
  def json4sNative(): Unit = {
    jRead[User](json)
  }

  @Benchmark
  def playJson(): Unit = {
    Json.fromJson[User](Json.parse(json))
  }
}

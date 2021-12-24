package com.github.gcnyin.benchmark

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.github.gcnyin.benchmark.models.User
import io.circe.generic.auto._
import io.circe.parser.decode
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import upickle.default.read
import org.json4s.NoTypeHints
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read => jRead}

@State(Scope.Benchmark)
class JsonParseTest {
  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  val json =
    """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China","base":"Shanghai","salary":1234.56}"""

  val upickleJson =
    """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China","base":["Shanghai"],"salary":1234.56,"favouriteFruit":[]}"""

  implicit val formats = Serialization.formats(NoTypeHints)

  @Benchmark
  def circeParse(): Unit = {
    decode[User](json)
  }

  @Benchmark
  def upickleParse(): Unit = {
    read[User](upickleJson)
  }

  @Benchmark
  def jacksonParse(): Unit = {
    mapper.readValue(json, new TypeReference[User] {})
  }

  @Benchmark
  def json4sParse(): Unit = {
    jRead[User](json)
  }
}

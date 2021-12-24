package com.github.gcnyin.benchmark

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.github.gcnyin.benchmark.models.User
import io.circe.generic.auto._
import io.circe.syntax._
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import upickle.default._
import org.json4s.NoTypeHints
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{write => jWrite}

@State(Scope.Benchmark)
class JsonSerializationTest {
  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  val user: User = User("Zhang san", 23, "Beijing, China", "China", Option("Shanghai"), 1234.56, Option.empty)

  implicit val formats = Serialization.formats(NoTypeHints)

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
  def json4sSerialization(): Unit = {
    jWrite(user)
  }
}

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
import play.api.libs.json.Json

@State(Scope.Benchmark)
class JsonSerialization {
  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  val user: User = User("Zhang san", 23, "Beijing, China", "China", Option("Shanghai"), 1234.56, Option.empty)

  implicit val formats = Serialization.formats(NoTypeHints)

  @Benchmark
  def circe(): Unit = {
    user.asJson.noSpaces
  }

  @Benchmark
  def upickleJson(): Unit = {
    write(user)
  }

  /**
    * not json, for reference only
    */
  @Benchmark
  def upickleMessagePack(): Unit = {
    writeBinary(user)
  }

  @Benchmark
  def jackson(): Unit = {
    mapper.writeValueAsString(user)
  }

  @Benchmark
  def json4sNative(): Unit = {
    jWrite(user)
  }

  @Benchmark
  def playJson(): Unit = {
    Json.toJson(user).toString()
  }
}

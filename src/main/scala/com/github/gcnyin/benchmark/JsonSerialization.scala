package com.github.gcnyin.benchmark

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.github.gcnyin.benchmark.models.User.default
import org.json4s.native.Serialization
import org.json4s.{Formats, NoTypeHints}
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}

@State(Scope.Benchmark)
class JsonSerialization {
  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  implicit val formats: Formats = Serialization.formats(NoTypeHints)

  @Benchmark
  def circe(): Unit = {
    import io.circe.generic.auto._
    import io.circe.syntax._

    default.asJson.noSpaces
  }

  @Benchmark
  def upickleJson(): Unit = {
    import upickle.default.write

    write(default)
  }

  @Benchmark
  def jackson(): Unit = {
    mapper.writeValueAsString(default)
  }

  @Benchmark
  def json4sNative(): Unit = {
    import org.json4s.native.Serialization.write

    write(default)
  }

  @Benchmark
  def playJson(): Unit = {
    import play.api.libs.json.Json

    Json.toJson(default).toString()
  }

  @Benchmark
  def sprayJson(): Unit = {
    import spray.json._

    default.toJson.toString
  }

  @Benchmark
  def zioJson(): Unit = {
    import zio.json._

    default.toJson
  }

  @Benchmark
  def jsoniter(): Unit = {
    import com.github.plokhotnyuk.jsoniter_scala.core._

    writeToArray(default)
  }
}

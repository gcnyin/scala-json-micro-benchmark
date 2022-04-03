package com.github.gcnyin.benchmark

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.github.gcnyin.benchmark.models.User
import com.github.gcnyin.benchmark.models.User.default
import org.json4s.native.Serialization
import org.json4s.{Formats, NoTypeHints}
import org.openjdk.jmh.annotations.{Benchmark, Scope, State}
import play.api.libs.json.Json
import upickle.default.{write, writeBinary}

@State(Scope.Benchmark)
class JsonParse {
  implicit val formats: Formats = Serialization.formats(NoTypeHints)

  val mapper: JsonMapper = JsonMapper.builder().addModule(DefaultScalaModule).build()

  val json: String = Json.toJson(default).toString()

  val upickleJsonString: String = write(default)

  val upickleMessagePackValue: Array[Byte] = writeBinary(default)

  @Benchmark
  def circe(): Unit = {
    import io.circe.generic.auto._
    import io.circe.parser.decode

    decode[User](json)
  }

  @Benchmark
  def upickleJson(): Unit = {
    import upickle.default.read

    read[User](upickleJsonString)
  }

  /** not json, for reference only
    */
  @Benchmark
  def upickleMessagePack(): Unit = {
    import upickle.default.readBinary

    readBinary[User](upickleMessagePackValue)
  }

  @Benchmark
  def jackson(): Unit = {
    import com.fasterxml.jackson.core.`type`.TypeReference

    mapper.readValue(json, new TypeReference[User] {})
  }

  @Benchmark
  def json4sNative(): Unit = {
    import org.json4s.native.Serialization.read

    read[User](json)
  }

  @Benchmark
  def playJson(): Unit = {
    Json.fromJson[User](Json.parse(json))
  }

  @Benchmark
  def sprayJson(): Unit = {
    import spray.json._

    json.parseJson.convertTo[User]
  }

  @Benchmark
  def zioJson(): Unit = {
    import zio.json._

    json.fromJson[User]
  }

  @Benchmark
  def jsoniter(): Unit = {
    import com.github.plokhotnyuk.jsoniter_scala.core._

    readFromArray[User](json.getBytes("UTF-8"))
  }
}

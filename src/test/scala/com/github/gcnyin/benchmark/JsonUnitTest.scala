package com.github.gcnyin.benchmark

import com.github.gcnyin.benchmark.models.User
import com.github.gcnyin.benchmark.models.User.default
import org.json4s.native.Serialization
import org.json4s.{Formats, NoTypeHints}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.{JsValue, Json}
import upickle.default.write

class JsonUnitTest extends AnyFlatSpec with Matchers {
  implicit val formats: Formats = Serialization.formats(NoTypeHints)

  val json: String = Json.toJson(default).toString()

  val upickleJson: String = write(default)

  "upickle" should "parse user" in {
    import upickle.default.read

    read[User](upickleJson, trace = true) should be(default)
  }

  "json4s" should "parse user" in {
    import org.json4s.native.Serialization.read

    read[User](json) should be(default)
  }

  "play-json" should "parse user" in {
    val jsonString: JsValue = Json.parse(json)
    val fromJson = Json.fromJson[User](jsonString)
    fromJson.isSuccess should be(true)
    fromJson.get should be(default)
  }

  "spray" should "parse user" in {
    import spray.json._

    val user: User = json.parseJson.convertTo[User]
    user should be(default)
  }

  "spray" should "stringify user" in {
    import spray.json._

    val j = default.toJson.toString

    val user: User = j.parseJson.convertTo[User]
    user should be(default)
  }

  "zio-json" should "parse user" in {
    import zio.json._

    val either = json.fromJson[User]
    either should be(Right(default))
  }

  "zio-json" should "stringify user" in {
    import zio.json._

    val either = default.toJson.fromJson[User]
    either should be(Right(default))
  }
}

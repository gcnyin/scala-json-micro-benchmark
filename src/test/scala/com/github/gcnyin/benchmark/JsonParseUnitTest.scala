package com.github.gcnyin.benchmark

import com.github.gcnyin.benchmark.models.User
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import upickle.default.read
import org.json4s.NoTypeHints
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read => jRead}
import play.api.libs.json.Json
import play.api.libs.json.JsValue
import play.api.libs.json.JsError
import play.api.libs.json.JsSuccess

class JsonParseUnitTest extends AnyFlatSpec with Matchers {
  implicit val formats = Serialization.formats(NoTypeHints)

  val json =
    """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China","base":"Shanghai","salary":1234.56}"""

  val upickleJson =
    """{"name":"Zhang san","age":23,"address":"Beijing, China","country":"China","base":["Shanghai"],"salary":1234.56,"favoriteFruit":[]}"""

  "upickle" should "parse user" in {
    read[User](upickleJson, trace = true).age should be(23)
  }

  "json4s" should "parse user" in {
    jRead[User](json).age should be(23)
  }

  "play-json" should "parse user" in {
    val jsonString: JsValue = Json.parse(json)
    val fromJson = Json.fromJson[User](jsonString)
    fromJson.isSuccess should be(true)
  }
}

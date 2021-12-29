package com.github.gcnyin.benchmark.models

import zio.json.{DeriveJsonEncoder, JsonEncoder}

case class User(
    name: String,
    age: Int,
    address: String,
    country: String,
    base: Option[String],
    salary: Double,
    favoriteFruit: Option[String],
    exEmployer: Seq[String]
)

object User {
  val default: User = User(
    "Zhang san",
    23,
    "Beijing, China",
    "China",
    Option("Shanghai"),
    1234.56,
    Option.empty,
    Vector("microsoft", "google", "apple", "facebook", "amazon")
  )

  import upickle.default.{ReadWriter, macroRW}
  implicit val upickleRW: ReadWriter[User] = macroRW

  import play.api.libs.json.{Format, Json}
  implicit val playJsonFormat: Format[User] = Json.format[User]

  import spray.json.DefaultJsonProtocol._
  import spray.json.RootJsonFormat
  implicit val sprayFormat: RootJsonFormat[User] = jsonFormat8(User.apply)

  import zio.json.{DeriveJsonDecoder, JsonDecoder}
  implicit val zioJsonDecoder: JsonDecoder[User] = DeriveJsonDecoder.gen[User]
  implicit val zioJsonEncoder: JsonEncoder[User] = DeriveJsonEncoder.gen[User]
}

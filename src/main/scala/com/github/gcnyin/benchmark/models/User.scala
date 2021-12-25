package com.github.gcnyin.benchmark.models

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
  implicit val rw: ReadWriter[User] = macroRW

  import play.api.libs.json.{Format, Json}
  implicit val format: Format[User] = Json.format[User]

  import spray.json.DefaultJsonProtocol._
  import spray.json.RootJsonFormat
  implicit val rootJsonFormat: RootJsonFormat[User] = jsonFormat8(User.apply)
}

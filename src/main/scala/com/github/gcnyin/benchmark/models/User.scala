package com.github.gcnyin.benchmark.models

import upickle.default.{ReadWriter, macroRW}
import play.api.libs.json.{Format, Json}

case class User(
    name: String,
    age: Int,
    address: String,
    country: String,
    base: Option[String],
    salary: Double,
    favoriteFruit: Option[String]
)

object User {
  implicit val rw: ReadWriter[User] = macroRW

  implicit val format: Format[User] = Json.format[User]
}

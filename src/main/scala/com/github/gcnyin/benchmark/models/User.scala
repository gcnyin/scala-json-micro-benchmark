package com.github.gcnyin.benchmark.models

import upickle.default.{ReadWriter, macroRW}

case class User(name: String, age: Int, address: String, country: String, base: Option[String], salary: Double, favouriteFruit: Option[String])

object User {
  implicit val rw: ReadWriter[User] = macroRW
}

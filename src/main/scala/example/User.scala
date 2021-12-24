package example

import upickle.default._

case class User(name: String, age: Int, address: String, country: String)

object User {
  implicit val rw: ReadWriter[User] = macroRW
}

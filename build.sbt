ThisBuild / scalaVersion := "2.13.7"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.gcnyin"
ThisBuild / organizationName := "json-micro-benchmark"

val upickleVersion = "1.4.3"
val circeVersion = "0.14.1"
val jacksonVersion = "2.13.1"
val json4sVersion = "4.0.3"

lazy val root = (project in file("."))
  .settings(
    name := "json-micro-benchmark",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "com.lihaoyi" %% "upickle" % upickleVersion,
      "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
      "org.json4s" %% "json4s-native" % json4sVersion,
      "org.scalatest" %% "scalatest" % "3.2.10" % Test
    ),
  )
  .enablePlugins(JmhPlugin)

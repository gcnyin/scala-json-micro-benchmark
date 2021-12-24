ThisBuild / scalaVersion := "2.13.7"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.gcnyin"
ThisBuild / organizationName := "json-micro-benchmark"

val upickleVersion = "1.4.3"
val circeVersion = "0.14.1"

lazy val root = (project in file("."))
  .settings(
    name := "json-micro-benchmark",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "com.lihaoyi" %% "upickle" % upickleVersion,
    ),
  )
  .enablePlugins(JmhPlugin)

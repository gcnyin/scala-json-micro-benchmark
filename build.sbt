ThisBuild / scalaVersion := "2.13.11"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.gcnyin"
ThisBuild / organizationName := "json-micro-benchmark"

lazy val root = (project in file("."))
  .settings(
    name := "json-micro-benchmark",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-parser" % "0.14.5",
      "io.circe" %% "circe-generic" % "0.14.5",
      "com.lihaoyi" %% "upickle" % "3.1.2",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.15.2",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.15.2",
      "org.json4s" %% "json4s-native" % "4.0.6",
      "com.typesafe.play" %% "play-json" % "2.10.0-RC9",
      "io.spray" %% "spray-json" % "1.3.6",
      "dev.zio" %% "zio-json" % "0.6.0",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.23.2",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.23.2" % "provided",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test
    )
  )
  .enablePlugins(JmhPlugin)

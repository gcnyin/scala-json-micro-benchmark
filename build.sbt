ThisBuild / scalaVersion := "2.13.7"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.gcnyin"
ThisBuild / organizationName := "json-micro-benchmark"

lazy val root = (project in file("."))
  .settings(
    name := "json-micro-benchmark",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-parser" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "com.lihaoyi" %% "upickle" % "1.5.0",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.2.2",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.2",
      "org.json4s" %% "json4s-native" % "4.0.4",
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "io.spray" %% "spray-json" % "1.3.6",
      "dev.zio" %% "zio-json" % "0.3.0-RC5",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.13.11",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.13.11" % "provided",
      "org.scalatest" %% "scalatest" % "3.2.11" % Test
    )
  )
  .enablePlugins(JmhPlugin)

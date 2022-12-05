ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.github.gcnyin"
ThisBuild / organizationName := "json-micro-benchmark"

lazy val root = (project in file("."))
  .settings(
    name := "json-micro-benchmark",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-parser" % "0.14.3",
      "io.circe" %% "circe-generic" % "0.14.3",
      "com.lihaoyi" %% "upickle" % "2.0.0",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.1",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1",
      "org.json4s" %% "json4s-native" % "4.0.6",
      "com.typesafe.play" %% "play-json" % "2.9.3",
      "io.spray" %% "spray-json" % "1.3.6",
      "dev.zio" %% "zio-json" % "0.4.2",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.18.1",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.18.1" % "provided",
      "org.scalatest" %% "scalatest" % "3.2.14" % Test
    )
  )
  .enablePlugins(JmhPlugin)

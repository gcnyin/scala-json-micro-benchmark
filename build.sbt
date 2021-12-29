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
      "com.lihaoyi" %% "upickle" % "1.4.3",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.1",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.1",
      "org.json4s" %% "json4s-native" % "4.0.3",
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "io.spray" %%  "spray-json" % "1.3.6",
      "dev.zio" %% "zio-json" % "0.2.0-M3",
      "org.scalatest" %% "scalatest" % "3.2.10" % Test
    ),
  )
  .enablePlugins(JmhPlugin)

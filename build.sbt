import Dependencies._

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "akka-blockchain",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "akka-blockchain",
    libraryDependencies ++= all
  )

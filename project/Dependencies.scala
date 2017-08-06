import sbt._

object Dependencies {
  val akkaV       = "2.5.3"
  val akkaHttpV   = "10.0.9"

  val akka = "com.typesafe.akka"

  lazy val all = Seq(
    akka %% "akka-actor" % akkaV,
    akka %% "akka-stream" % akkaV,
    akka %% "akka-testkit" % akkaV,
    akka %% "akka-http" % akkaHttpV,
    akka %% "akka-http-spray-json" % akkaHttpV,
    akka %% "akka-http-testkit" % akkaHttpV,
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  )
}

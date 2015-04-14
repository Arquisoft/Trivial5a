name := """C:\Trivial5a\WebTrivial5a"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.0.akka23"
)

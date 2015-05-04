name := """C:\Trivial5a\WebTrivial5a"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0",
  "org.mongojack" % "mongojack" % "2.0.0-RC1",
  "info.cukes" % "cucumber-java" % "1.1.8" % "test",
  "info.cukes" % "cucumber-junit" % "1.1.8" % "test",
  "org.assertj" % "assertj-core" % "2.0.0" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.39.0",
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % "2.39.0",
  "org.seleniumhq.selenium" % "htmlnit-driver" % "2.39.0",
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.webjars" % "angularjs" % "1.3.8",
  "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
  "org.mockito" % "mockito-core" % "1.10.17" % "test",
  "com.google.code.gson" % "gson" % "2.3.1",
  "org.mongodb" % "mongo-java-driver" % "2.13.0"
)

fork in run := false


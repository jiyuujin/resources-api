name := """resources-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  jdbc,
  guice,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
)

PlayKeys.playRunHooks += PlayDevRunHook(file("./front"))

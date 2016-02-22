name := "duck-soup"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies := Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.json4s" % "json4s-native_2.11" % "3.3.0",
  "org.jsoup" % "jsoup" % "1.8.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)
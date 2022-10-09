ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "wordcount-2",
    idePackagePrefix := Some("ok.ml")
  )

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

val hdpVersion = "2.10.1"
libraryDependencies ++= Seq("hdfs", "mapreduce-client-core", "common")
  .map(x => "org.apache.hadoop" % s"hadoop-$x" % hdpVersion % "provided")

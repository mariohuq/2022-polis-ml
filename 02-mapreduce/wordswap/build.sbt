ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.14"

lazy val root = (project in file("."))
  .settings(
    name := "wordswap"
  )

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

val hdpVersion = "2.10.1"
libraryDependencies ++= Seq("hdfs", "mapreduce-client-core", "common")
  .map(x => "org.apache.hadoop" % s"hadoop-$x" % hdpVersion % "provided")

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.12" // since no spark core is on Maven for recent Scala version
                                      // AND spark is running on that specific version

lazy val root = (project in file("."))
  .settings(
    name := "WordCount",
    idePackagePrefix := Some("ok.ml")
  )

libraryDependencies ++= Seq("org.apache.spark" %% "spark-sql" % "2.4.8" % "provided")

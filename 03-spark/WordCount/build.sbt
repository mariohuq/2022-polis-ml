ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.12" // since no spark core is on Maven for recent Scala version
                                      // AND spark is running on that specific version

lazy val root = (project in file("."))
  .settings(
    name := "WordCount",
    idePackagePrefix := Some("ok.ml")
  )

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.4.8")

organization       := "tv.cntt"

name               := "scaposer"

version            := "1.5"

scalaVersion       := "2.11.2"

crossScalaVersions := Seq("2.11.2", "2.10.4")

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

// http://www.scala-sbt.org/release/docs/Detailed-Topics/Java-Sources
// Avoid problem when this lib is built with Java 7 but the projects that use it
// are run with Java 6
// java.lang.UnsupportedClassVersionError: Unsupported major.minor version 51.0
javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

// Scala 2.11 does not include scala.util.parsing.combinator
libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value :+ "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"
    case _ =>
      libraryDependencies.value
  }
}

libraryDependencies += "org.specs2" %% "specs2" % "2.3.13" % "test"

//------------------------------------------------------------------------------

// Skip API doc generation to speedup "publish-local" while developing.
// Comment out this line when publishing to Sonatype.
publishArtifact in (Compile, packageDoc) := false

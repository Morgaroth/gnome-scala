
val Versions = new {
  val scalatest = "3.1.0"
  val scalaLogging = "3.9.2"
  val cats = "2.1.0"
}

val validate = Def.taskKey[Unit]("Validates entire project")

val root = project.in(file("."))
  .enablePlugins(MdocPlugin)
  .settings(
    name := "gnome-scala",
    organization := "io.morgaroth",
    scalaVersion := "2.13.3",

    crossScalaVersions := Seq("2.12.12", "2.13.3"),

    resolvers += "Artifactory" at "https://mateuszjajedev.jfrog.io/artifactory/maven/",

    libraryDependencies ++= Seq(
      "io.morgaroth" % "java-gnome" % "4.1.2" % Provided,
      //      "java" % "java-gnome" % "4.1.2" from "file:///usr/share/java/gtk.jar",
      "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging,
      "org.typelevel" %% "cats-core" % Versions.cats,

      "org.scalatest" %% "scalatest" % Versions.scalatest % Test,
    ),

    // docs
    mdocIn := baseDirectory.value,
    mdoc := {
      mdoc.toTask(" Readme.md").value
      Nil
    },

    Test / fork := true,

    //Compile / compile := ((Compile / compile) dependsOn tut).value
    Test / publishArtifact := false,

    // Bintray
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),

    // Release
    releaseTagComment := s"Releasing ${(ThisBuild / version).value} [skip ci]",
    releaseCommitMessage := s"Setting version to ${(ThisBuild / version).value} [skip ci]",
    releaseNextCommitMessage := s"Setting version to ${(ThisBuild / version).value} [skip ci]",
    releaseCrossBuild := true,
    publishMavenStyle := true,
    credentials += Credentials(file(sys.env.getOrElse("JFROG_CREDENTIALS_FILE", ".credentials"))),
    versionScheme := Some("semver-spec"),
    publishTo := Some {
      if (isSnapshot.value) "Artifactory releases" at "https://mateuszjajedev.jfrog.io/artifactory/maven/"
      else "Artifactory snapshots" at s"https://mateuszjajedev.jfrog.io/artifactory/maven;build.timestamp=${new java.util.Date().getTime}"
    },

    validate := Def.task {
      (Test / test).value
      //      tut.value
    }.value
  )

val examples = project.in(file("examples"))
  .dependsOn(root)
  .settings(
    scalaVersion := "2.13.1",
    publishArtifact := false
  )
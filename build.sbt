

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

    resolvers += Resolver.bintrayRepo("morgaroth", "maven"),

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

    fork in Test := true,

    //Compile / compile := ((Compile / compile) dependsOn tut).value
    publishArtifact in Test := false,

    // Bintray
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    bintrayVcsUrl := Some("https://gitlab.com/morgaroth/gnome-scala.git"),

    // Release
    releaseTagComment := s"Releasing ${(version in ThisBuild).value} [skip ci]",
    releaseCommitMessage := s"Setting version to ${(version in ThisBuild).value} [skip ci]",
    releaseNextCommitMessage := s"Setting version to ${(version in ThisBuild).value} [skip ci]",
    releaseCrossBuild := true,

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


val Versions = new {
  val scalatest = "3.0.5"
  val scalaLogging = "3.9.0"
  val cats = "1.1.0"
}

val validate = Def.taskKey[Unit]("Validates entire project")

val root = project.in(file("."))
  .enablePlugins(TutPlugin)
  .settings(
    name := "gnome-scala",
    organization := "io.morgaroth",
    scalaVersion := "2.12.7",

    resolvers += Resolver.bintrayRepo("morgaroth", "maven"),

    libraryDependencies ++= Seq(
      "io.morgaroth" % "java-gnome" % "4.1.2",
      "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging,
      "org.typelevel" %% "cats-core" % Versions.cats,
    ),

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % Versions.scalatest % Test,
      "org.json4s" %% "json4s-native" % "3.5.0" % Test,
    ),

    // docs
    tutSourceDirectory := baseDirectory.value,
    tut := {
      tutOnly.toTask(" Readme.md").value
      Nil
    },

    //Compile / compile := ((Compile / compile) dependsOn tut).value
    publishArtifact in Test := false,

    // Bintray
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    bintrayVcsUrl := Some("https://gitlab.com/morgaroth/op-rabbit-rpc.git"),

    // Release
    releaseTagComment := s"Releasing ${(version in ThisBuild).value} [skip ci]",
    releaseCommitMessage := s"Setting version to ${(version in ThisBuild).value} [skip ci]",

    validate := Def.task {
      (Test / test).value
      tut.value
    }.value
  )


val examples = project.in(file("examples"))
  .dependsOn(root)
  .settings(
    publishArtifact := false
  )
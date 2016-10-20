import sbt.Keys._
import sbt._

object Common extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = plugins.JvmPlugin

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    scalaVersion := "2.11.8",
    crossScalaVersions := Seq("2.11.8", "2.12.0-RC2"),
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-feature",
      "-Xlint",
      "-Xexperimental",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-language:experimental.macros"
    ),
    updateOptions := updateOptions.value.withCachedResolution(true),
    incOptions := incOptions.value.withLogRecompileOnMacro(false)
  )

}

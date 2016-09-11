import sbt.Keys._
import sbt._
import scalaprops.ScalapropsPlugin.autoImport._

object Dependencies extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = Common

  object autoImport {
    val configVersion = settingKey[String]("Typesafe config version")
    val lombokVersion = settingKey[String]("lombok version")
    val java8CompatVersion = settingKey[String]("scala-java8-compat version")
  }

  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    configVersion := "1.3.0",
    lombokVersion := "1.16.10",
    scalapropsVersion := "0.3.4",
    java8CompatVersion := (scalaVersion.value match {
      case "2.12.0-RC1" => "0.8.0-RC5"
      case _ => "0.7.0"
    })
  )

  lazy val java8Compat = Def.setting {
    "org.scala-lang.modules" %% "scala-java8-compat" % java8CompatVersion.value
  }

  lazy val core =
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % configVersion.value,
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided",
      "org.projectlombok" % "lombok" % lombokVersion.value % "test",
      java8Compat.value % "test"
    )

  lazy val testutil =
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % configVersion.value,
      "com.github.scalaprops" %% "scalaprops" % scalapropsVersion.value,
      "org.projectlombok" % "lombok" % lombokVersion.value
    )

  lazy val docs =
    libraryDependencies ++= Seq(
      "org.projectlombok" % "lombok" % lombokVersion.value % "test"
    )

}

import com.github.retronym.SbtOneJar._

oneJarSettings

name := """ai2-challenge"""
version := "1.0"
scalaVersion := "2.11.7"

libraryDependencies += "com.github.scopt" %% "scopt" % "3.3.0"
libraryDependencies in Test += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

resolvers += Resolver.sonatypeRepo("public")

mainClass := Some("AppMain")

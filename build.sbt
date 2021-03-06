name := "AuthzVerifyProxy"
organization := "dtlaboratory.com"
javacOptions ++= Seq("-source", "1.11", "-target", "1.11") 
scalacOptions ++= Seq(
  "-target:jvm-1.8"
)
fork := true
javaOptions in test ++= Seq(
  "-Xms128M", "-Xmx256M",
  "-XX:+CMSClassUnloadingEnabled"
)

version := "0.1.0"

parallelExecution in test := false

crossScalaVersions := List("2.13.5")
version := "1.0"

val akkaHttpVersion = "10.2.4"
val akkaVersion = "2.6.13"
val swaggerVersion = "2.0.8"

inThisBuild(List(
  organization := "dtlaboratory.com",
  homepage := Some(url("https://github.com/dtlaboratory/authz-verify-proxy")),
  licenses := List("MIT" -> url("https://github.com/dtlaboratory/authz-verify-proxy/blob/master/LICENSE")),
  developers := List(
    Developer(
      "navicore",
      "Ed Sweeney",
      "ed@onextent.com",
      url("https://navicore.tech")
    )
  )
))

libraryDependencies ++=
  Seq(
    "com.auth0" % "jwks-rsa" % "0.15.0",
    "com.auth0" % "java-jwt" % "3.13.0",
    "ch.megard" %% "akka-http-cors" % "1.1.1",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe" % "config" % "1.4.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "org.json4s" %% "json4s-native" % "3.6.10",
    "com.github.nscala-time" %% "nscala-time" % "2.26.0",
    "org.scalatest" %% "scalatest" % "3.2.4" % "test"
  )

assemblyJarName in assembly := s"${name.value}.jar"

assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case x if x.endsWith("io.netty.versions.properties") => MergeStrategy.first
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}


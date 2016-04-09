name := "akka-http-rest"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.bintrayRepo("hseeberger", "maven")
)

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-http-core" % "2.4.3",
    "com.typesafe.akka" %% "akka-http-experimental" % "2.4.3",
    "de.heikoseeberger" %% "akka-http-json4s" % "1.5.3"
  )
}
    
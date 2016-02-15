name := "MohanLearningGround"

version := "1.0"

scalaVersion := "2.11.7"

transitiveClassifiers := Seq("sources")

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime" withSources(),
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test" withSources(),
  "org.scalacheck" % "scalacheck_2.11" % "1.12.2"  withSources() 
)

logLevel := Level.Debug

libraryDependencies <++= (scalaVersion)(sv =>
  Seq(
    "org.apache.commons" % "commons-io" % "1.3.2" withSources(),
    "commons-lang" % "commons-lang" % "2.6" withSources(),
    "junit" % "junit" % "4.12"
      ))
 
resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
 
libraryDependencies += "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.1.0"
 
libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.scala-lang" % "scala-compiler" % scalaVersion.value,
	"org.scala-lang" % "scala-reflect" % scalaVersion.value,    
    "org.slf4j" % "slf4j-api" % "1.7.5" withSources(),
    "org.slf4j" % "slf4j-simple" % "1.7.5" withSources(),
    "org.scala-lang.modules" %% "scala-async" % "0.9.2"
  )
  

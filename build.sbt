name := "swiffer"

//packageSummary := "Job to cipher and anonymize"

//packageDescription := ""

version := "0.1"

scalaVersion := "2.11.5"

val sparkVersion = "2.3.0"

parallelExecution in Test := false

resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  "org.apache.spark"              %% "spark-core"         % sparkVersion % "provided" withSources() withJavadoc(),
  "org.apache.spark"              %% "spark-sql"          % sparkVersion % "provided" withSources() withJavadoc(),
  "org.apache.spark"              %% "spark-hive"         % sparkVersion % "provided" withSources() withJavadoc(),
  "com.github.kxbmap"             %% "configs"            % "0.4.4",
  "com.github.scopt"              %% "scopt"              % "3.7.0",
  "org.scalatest"                 %% "scalatest"          % "3.0.5",
  "com.holdenkarau"               %% "spark-testing-base" % "2.3.0_0.9.0" % "test" excludeAll(
    ExclusionRule(organization = "org.scalacheck"),
    ExclusionRule(organization = "org.scalactic"),
    ExclusionRule(organization = "org.scalatest")
  )
)
        
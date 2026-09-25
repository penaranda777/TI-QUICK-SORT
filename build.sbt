ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "TI-QUICK-SORT",
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
import ReleaseTransformations._
import com.typesafe.sbt.pgp

name := "sbt-release-sample"

scalaVersion := "2.12.4"

organization := "uk.co.callhandling"

moduleName := "test-release"

pomIncludeRepository := { (repo: MavenRepository) =>
   repo.root.startsWith("file:")
}

licenses := Seq("Apache 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))

homepage := Some(url("https://github.com/geekminer/sbt-release-sample"))

scmInfo := homepage.value.map(url => ScmInfo(
  url,
  "scm:git@github.com:pierrebruninmaif/sbt-release-sample.git"
))

publishMavenStyle := true

publishArtifact in Test := false

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runClean,                               // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  pushChanges

)

releaseUseGlobalVersion := false
/*
PgpKeys.pgpSelectPassphrase in Global := pgpPassphrase.value orElse
  (Credentials.forHost(credentials.value, "pgp") map (_.passwd.toCharArray))*/

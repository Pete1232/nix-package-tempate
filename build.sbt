val projectName = "nix-package-template"

val sharedSettings = Seq(
  organization := "com.pete1232",
  scalaVersion := "2.13.8",
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/pete1232/nix-package-template"),
      "https://github.com/pete1232/nix-package-template.git"
    )
  )
)

lazy val root = (project in file("."))
  .settings(
    name := projectName,
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    addCommandAlias(
      "runCore",
      "core/run"
    )
  )
  .settings(sharedSettings)
  .aggregate(core)

lazy val core = (project in file("core"))
  .settings(
    name := s"${projectName}-core",
    assembly / assemblyJarName := s"${projectName}-assembly-core.jar"
  )
  .settings(sharedSettings)

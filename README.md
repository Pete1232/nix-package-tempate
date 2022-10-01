# nix-package-template

Experiment to attempt to build a Scala application using Nix as much
as possible. Running `nix build` will create a jar and package it as
a Docker compatible `tar` file. The only dependency to doing this
should be [installing Nix](https://nixos.org/download.html). SBT is
being used to download the application dependencies, doing that with
Nix would require a lot more dedication.

Instructions below on how to run this package locally. When creating
a tag in Github it will publish this to GH packages so it can be
downloaded like any other container.

## development environment

Start a nix shell with

```sh
nix develop -i
```

## build and run locally

To run the full build

```sh
nix develop -i
> genericBuild
```

or as it runs in CI (make sure to delete all /target directories if running locally, and can still be flaky)

```sh
nix build --json
#  [{"drvPath":"/nix/store/34b4iqha5sjaj5dbbk3jw6k024dw02y5-docker-image-nix-package-template.tar.gz.drv","outputs":{"out":"/nix/store/dhmjfmlfp2pjsp2i0jkssa4kn2xqn6pm-docker-image-nix-package-template.tar.gz"}}]

OUT=$(nix build --json | jq -r ".[] | .outputs.out")

docker load -i $OUT
# Loaded image: nix-package-template:latest

docker run nix-package-template:latest
```

On push to a pull request branch or merge to master this will
run in GH Actions pretty much as described here.

On tag it will also push to GH Packages.

# SDK Documentation

## Release notes

To see in depth SDK documentation press the release number on first column. To see full detail of release notes press the link
in the second column.

| Release                   | Notes                                               | Summary                                                                                                                           |
|---------------------------|:----------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------|
| [0.1.0](0.1.0/index.html) | [0.1.0 release notes](notes/release_0.1.0_notes.md) | Initial release |


## Installation

### Gradle

To add the dependency in Gradle, just add the following line inside `dependencies` tag:

```
compile 'org.kryptokrona.sdk:kryptokrona-sdk:0.1.0'
```

### Maven

To add our dependency to your Maven build tool, add this inside the `<dependencies>` tag:

```xml
<dependency>
	<groupId>org.kryptokrona.sdk</groupId>
	<artifactId>kryptokrona-sdk</artifactId>
	<version>0.1.0</version>
</dependency>
```

### Manually

If there is a need for not using a repository such as Maven Central we can import
it manually, although in most cases it's recommended to use a repository. Use this only
if you are sure that is needed.

Open up the file **build.gradle** and add the following:

```groovy
buildscript {
   repositories {
      mavenCentral()
      flatDir {
         dirs 'libs'
      }
   }
   dependencies {
      classpath("org.kryptokrona.sdk:kryptokrona-sdk:0.1.0")
   }
}
```

Then add the .jar file you downloaded from our GitHub Packages to the dependency block:

```groovy
dependencies {
    implementation files('libs/kryptokrona-sdk-<version.jar')
}
```


## Usage

For usages please see the **sdk-examples** submodule with the `README.md` file.


## Setup Testnet

If you don't want to use a remote node in order to test the implementation you could setup your own testnet. To setup
run the command:
g
- `./sdk-scripts/start-testnet.sh`

This will fetch the Docker Image from Docker Hub and orchestrate up a testnet.

If you want to stop the containers:

- `./sdk-scripts/teardown-testnet.sh`

If you want to remove everything locally with Docker (use with caution):

- `./sdk-scripts/remove-testnet.sh`


## Help and Support

The best place to get help and support is to join our Discord server. Please join us here: https://discord.gg/mkRpVgDubC

## FAQ

- Q: Why use Java and not for example, JavaScript?
    - A: The biggest reason why we decided to go with Java is that the language is very stable, scalable, good performance, active evolution, good for integrating in mobile applications as well as enterprice software that usually runs on Java.

## License

The license is GPL-3.0 License.
# Kryptokrona SDK

![Kryptokrona SDK](kryptokrona-logo.png)

Kryptokrona SDK in Java for building decentralized private communication and payment systems.

<p>
<a href="https://chat.kryptokrona.org">
    <img src="https://img.shields.io/discord/562673808582901793?label=Discord&logo=Discord&logoColor=white&style=flat">
</a>
<a href="https://github.com/kryptokrona/kryptokrona-sdk/issues">
    <img src="https://img.shields.io/github/issues/kryptokrona/kryptokrona-sdk">
</a>
<a href="https://github.com/kryptokrona/kryptokrona-sdk/pulls">
    <img src="https://img.shields.io/github/issues-pr/kryptokrona/kryptokrona-sdk">
</a>
<a href="https://github.com/kryptokrona/kryptokrona-sdk/commits/main">
    <img src="https://img.shields.io/github/commit-activity/m/kryptokrona/kryptokrona-sdk">
</a>
<a href="https://github.com/kryptokrona/kryptokrona-sdk/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/kryptokrona/kryptokrona-sdk">
</a>
<a href="https://github.com/kryptokrona/kryptokrona-sdk/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/kryptokrona/kryptokrona-sdk">
</a>
<a href="https://twitter.com/kryptokrona">
    <img src="https://img.shields.io/twitter/follow/kryptokrona">
</a>
</p>

## Technologies

- OpenJDK 17 (LTS)
- Gradle
- RxJava
- JUnit5
- Lombok

## Installation

### Gradle

To add the dependency in Gradle, just add the following line inside `dependencies` tag:

```
compile 'org.kryptokrona.sdk:kryptokrona-sdk:1.0.0'
```

### Maven

To add our dependency to your Maven build tool, add this inside the `<dependencies>` tag:

```xml
<dependency>
	<groupId>org.kryptokrona.sdk</groupId>
	<artifactId>kryptokrona-sdk</artifactId>
	<version>1.0.0</version>
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
      classpath("org.kryptokrona.sdk:kryptokrona-sdk:1.0.0")
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

```java
import org.kryptokrona.sdk.wallet.Wallet;

public class Main {
    public static void main(String[] args) {
        Wallet wallet = new Wallet();
    }
}
```

## Unit Testing

We are using JUnit 5 to conduct our tests. All tests are located under `src/test`.

To run unit tests run:

- `./gradlew test`

## Static Code Analysis

## Code Coverage

## Build, Test and Deployment

We are using Gradle as the build tool for this project. The easiest way to build if you are not running IntelliJ is to run
the command:

- `./gradlew build`

This command will also run all the unit tests at the same time so if you want to avoid that, just run the command:

- `./gradlew assemble`

There might be some trouble doing this multiple times, then we need to do a cleanup:

- `./gradlew clean`

### CI/CD

We also have a CI/CD flow for this project that will assemble the code, run static code analysis,
run unit tests, run code coverage, and save the artifact of the build to GitHub Packages.

This artifact can then be downloaded and imported manually if that need exists for the project. Instructions
on how to do this can be read in the installation instructions above.

Whenever you submit a pull request a pipeline will run with the following steps:

- Assemble
- Static Code Analysis
- Unit Test
- Code Coverage

The only thing different here from the main pipeline that runs is that we do not publish an artifact to
GitHub Packages. 

## Contribute

If you would like to contribute to this project there is two ways:

- Send a pull request
- Donate to XKR address

### Pull request

We appreciate all contributions whether it be small changes such as documentation of source code to major improvement of code. The easiest way is to make a fork and then make a pull request into our develop branch. To make the PR go through make sure to include this information:

```
What does this PR do?

Why are these changes required?

This PR has been tested using (e.g. Unit Tests, Manual Testing):

Extra details?
```

### Donate

XKR: SEKReXXU9aJPiwjX2XkpbK8ACMWbUNXcYPxUVSiUYpNdhj8Z2snEy8CjjorZUNyswQNfzAmVWuGksU72Sf3Kq79Zd3fJWHq4Nyx

![Kryptokrona QR Code](qr-code.png)

## Help and Support

The best place to get help and support is to join our Discord server. Please join us here: https://discord.gg/mkRpVgDubC

## FAQ

## Contributors

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/mjovanc"><img src="https://avatars.githubusercontent.com/u/33717111?v=4" width="100px;" alt=""/><br /><sub><b>Marcus Cvjeticanin</b></sub></a><br /></td>
   </td>
  </tr>
  </tr>

</table>

## License

The license is GPL-3.0 License.
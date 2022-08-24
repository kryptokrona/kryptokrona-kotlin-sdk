# <img src="https://raw.githubusercontent.com/kryptokrona/kryptokrona-python-sdk/master/kryptokrona.png" alt="XKR" height="36" /> Kryptokrona SDK [![Kryptokrona SDK Main Pipeline](https://github.com/kryptokrona/kryptokrona-java-sdk/actions/workflows/main-ci.yml/badge.svg)](https://github.com/kryptokrona/kryptokrona-java-sdk/actions/workflows/main-ci.yml) 

Kryptokrona is a decentralized blockchain from the Nordic based on CryptoNote, which forms the basis for Monero, among others. CryptoNote is a so-called “application layer” protocol further developed by TurtleCoin that enables things like: private transactions, messages and arbitrary data storage, completely decentralized.

Kryptokrona SDK for Java is currently the most fully featured implementation of the Kryptokrona Network protocols and includes many useful components and tools for building decentralized private communication and payment systems.

## Table of Contents

- [Development resources](#development-resources)
- [Dependencies](#dependencies)
- [Installation](#installation)
- [Tests](#tests)
    - [Unit Testing](#unit-testing)
    - [Static Code Analysis](#static-code-analysis)
    - [Code Coverage](#code-coverage)
- [Build](#build)
  - [CI/CD](#cicd)
- [GitHub Pages](#github-pages)
  - [Javadoc](#javadoc)
- [Contribute](#contribute)
  - [Pull Request](#pull-request)
  - [Donate](#donate)
- [Contributors](#contributors)
- [License](#license)


## Development Resources

- Web: kryptokrona.org
- Mail: mjovanc@icloud.com
- GitHub: https://github.com/kryptokrona
- Hugin: sdkdevs board on Hugin Messenger
- It is HIGHLY recommended to join our board on Hugin Messenger if you want to contribute to stay up to date on what is happening on the project.

## Dependencies

The following table summarizes the tools and libraries required to build.

| Dep                | Min. version | Optional | Purpose                                     |
|--------------------|--------------|----------|---------------------------------------------|
| OpenJDK            | 17           | NO       | Java Development Kit                        |
| Gradle             | 7.4          | NO       | Build Tool                                  |
| RxJava             | 3.1.4        | NO       | Functional Reactive Programming             |
| Google HTTP Client | 1.41.8       | NO       | HTTP Request Library                        |
| JUnit              | 5.8.2        | NO       | Unit Testing Library                        |
| Lombok             | 1.18.24      | NO       | Lombok Library                              |
| JaCoCo             | any          | NO       | Code Coverage Library                       |
| Checkstyle         | any          | NO       | Styleguide checks                           |
| PMD                | any          | NO       | Source Code Analyzer                        |
| Spotbugs           | 5.0.4        | NO       | Find Bugs                                   |
| gson               | 2.9.0        | NO       | JSON Parser                                 |
| IPAddress          | 5.3.4        | NO       | IPAddress Handling IP Addresses and Subnets |
| Spring             | 2.5.2        | NO       | Spring Framework                            |
| Hibernate          | any          | NO       | ORM Framework                               |
| PostgreSQL         | 1.6.17       | NO       | Database to Store Wallet(s) data            |

## Installation

To get it up and running locally you need to clone the repository:

Using SSH:

- `git clone git@github.com:kryptokrona/kryptokrona-sdk.git`

Using HTTPS:

- `git clone https://github.com/kryptokrona/kryptokrona-sdk.git`

## Tests

We use unit tests, static code analysis and code coverage to ensure that we always write
quality code. We can either run them seperatly to view the status or run everything with
one command:

- `./gradlew test`

### Unit Testing

We are using JUnit 5 to conduct our unit tests. All tests are located under `src/test`.

To run unit tests run:

- `./gradlew test`

An HTML file will be generated so that we can open up in our browser to
view in more detail the test results. The file is located in `build/reports/tests/test/index.html`

### Static Code Analysis

The static code analysis tool used in this project is cpd. All configuration for the static code analysis can be found 
in `gradle/static-code-analysis`. To run the static code analysis:

- `./gradlew checkStyleMain`
- `./gradlew cpdCheck`
- `./gradlew pmdMain`
- `./gradlew spotbugsMain`

HTML files will be generated so that we can open up in our browser. The files 
are located in:

- `build/reports/checkstyle/main.html`
- `build/reports/spotbugs/main/spotbugs.html`
- `build/reports/pmd/main.html`

### Code Coverage

The code coverage tool of this project is JaCoCo. To run:

- `./gradlew jacacoTestReport`

An HTML file will be generated so that we can open up in our browser. The file
is located in `build/kryptokrona-sdk-report/index.html`

## Build

We are using Gradle as the build tool for this project. The easiest way to build if you are not running IntelliJ is to run
the command:

- `./gradlew build`

This command will also run all the unit tests at the same time so if you want to avoid that, just run the command:

- `./gradlew assemble`

There might be some trouble doing this multiple times, then we need to do a cleanup:

- `./gradlew clean`

If you want to visualize how the Gradle tasks are run in what order 
by their dependencies. Run the command:

- `./gradlew taskTree`

To list all the tasks provided in this project run:

- `./gradlew tasks`

**Tip:** you can also run the commands in short form e.g. `./gradlew tT` or `./gradlew jTR` that is 
for the jacocoTestReport task.

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

## GitHub Pages

We use GitHub Pages for displaying the documentation of this project in addition to this README file. Go to https://kryptokrona.github.io/kryptokrona-sdk/
to view all the links to the releases and other additional information.

### Javadoc

We use Javadoc for our in depth documentation that is available on GitHub Pages. To generate javadoc and getting these into
version control so it can get deployed we need to run:

- `./gradlew copyJavaDoc`

This has a dependency to javadoc gradle task so we don't need to run two commands.

**NOTE:** The files will be located under `docs/<version>` and the version is picked up by what it says inside the file 
`gradle.properties`.

## Contribute

If you would like to contribute to this project there is two ways:

- Send a pull request
- Donate to XKR address

### Pull request

We appreciate all contributions whether it be small changes such as documentation of source code to major improvement of code.

The easiest way is to make a fork and then make a pull request into our develop branch. To make the PR go through make sure to include this information:

```
What does this PR do?

Why are these changes required?

This PR has been tested using (e.g. Unit Tests, Manual Testing):

Extra details?
```

**NOTE:** Remember to update existing diagrams if there is some bigger improvements of code so it's up to date with the implementation.

### Donate

XKR: SEKReXXU9aJPiwjX2XkpbK8ACMWbUNXcYPxUVSiUYpNdhj8Z2snEy8CjjorZUNyswQNfzAmVWuGksU72Sf3Kq79Zd3fJWHq4Nyx

![Kryptokrona QR Code](qr-code.png)

## Contributors

The following contributors have either helped to start this project, have contributed
code, are actively maintaining it (including documentation), or in other ways
being awesome contributors to this project. **We'd like to take a moment to recognize them.**

[<img src="https://github.com/mjovanc.png?size=72" alt="mjovanc" width="72">](https://github.com/mjovanc)

## License

The license is GPL-3.0 License.

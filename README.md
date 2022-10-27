![Kryptokrona SDK](resources/logo_github.png)

[![Kryptokrona SDK Main Pipeline](https://github.com/kryptokrona/kryptokrona-sdk/actions/workflows/main-ci.yml/badge.svg)](https://github.com/kryptokrona/kryptokrona-java-sdk/actions/workflows/main-ci.yml) 
[![Release](https://img.shields.io/github/release-date/kryptokrona/kryptokrona-sdk)](https://img.shields.io/github/release-date/kryptokrona/kryptokrona-sdk)

Kryptokrona is a decentralized blockchain from the Nordic based on CryptoNote, which forms the basis for Monero, among others. CryptoNote is a so-called “application layer” protocol further developed by TurtleCoin that enables things like: private transactions, messages and arbitrary data storage, completely decentralized.

Kryptokrona SDK for Java is currently the most fully featured implementation of the Kryptokrona Network protocols and includes many useful components and tools for building decentralized private communication and payment systems.

## Table of Contents

- [Development resources](#development-resources)
- [Releases](#releases)
- [Installation and Getting Started](#installation-and-getting-started)
- [Getting Help](#getting-help)
- [Reporting Issues](#reporting-issues)
- [Guides](#guides)
- [Contribute](#contribute)
  - [Pull Request](#pull-request)
  - [Donate](#donate)
- [Contributors](#contributors)
- [License](#license)


## Development Resources

- Web: https://kryptokrona.org
- Docs: https://docs.kryptokrona.org
- Mail: mjovanc@icloud.com
- GitHub: https://github.com/kryptokrona
- Hugin: sdkdevs board on Hugin Messenger
- It is HIGHLY recommended to join our board on Hugin Messenger if you want to contribute to stay up to date on what is happening on the project.

## Releases

| NAME     | RELEASE DATE | VERSION | MAINTAINER         | EOL     | DESCRIPTION              |
|----------|--------------|---------|--------------------|---------|--------------------------|
| Mjölnir | Not set      | 0.1.0   | Marcus Cvjeticanin | Not set | To be done.              |

## Installation and Getting Started

To install Kryptokrona SDK into your Maven/Gradle project we need to include the dependency:

**Maven**
```xml
<dependency>
    <groupId>org.kryptokrona.sdk</groupId>
    <artifactId>kryptokrona-sdk</artifactId>
    <version>0.1.0</version>
    <scope>implementation</scope>
</dependency>
```

**Gradle**
```gradle
dependencies {
    implementation 'org.kryptokrona.sdk:0.1.0'
}
```

**Gradle Kotlin DSL**
```kotlin
dependencies {
    implementation("org.kryptokrona:sdk:0.1.0")
}
```

Basic example of how to create a Kryptokrona Wallet:

```java
public class Example {
  public static void main(String[] args) throws IOException, InterruptedException {
    // initialize a daemon
    var daemon = new DaemonImpl(new HostName("swepool.org:11898"), false);

    // initialize a wallet service
    var walletService = new WalletService(daemon);

    // start wallet service sync
    walletService.start();

    // create a new wallet
    var wallet = walletService.createWallet();

    // save the wallet to a file
    walletService.saveWalletToFile(wallet, "mjovanc");

    // sleep to keep processing more blocks before we stop it
    Thread.sleep(5000);

    // stop the wallet sync
    walletService.stop();
  }
}
```

## Getting Help

Are you having trouble with Kryptokrona SDK? We want to help!

- Check the JavaDoc documentation: https://kryptokrona.github.io/kryptokrona-sdk/0.1.0/index.html

- If you are upgrading, read the release notes for upgrade instructions and "new and noteworthy" features.

- Ask a question we monitor stackoverflow.com for questions tagged with kryptokrona-sdk. You can also chat with the community on Hugin or Discord.

- Report bugs with Kryptokrona SDK at github.com/kryptokrona/kryptokrona-sdk/issues.

## Reporting Issues

Kryptokrona SDK uses GitHub’s integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

- Before you log a bug, please search the issue tracker to see if someone has already reported the problem.

- If the issue doesn’t already exist, create a new issue.

- Please provide as much information as possible with the issue report. We like to know the Kryptokrona SDK version, operating system, and JVM version you’re using.

- If you need to paste code or include a stack trace, use Markdown. ``` escapes before and after your text.

- If possible, try to create a test case or project that replicates the problem and attach it to the issue.

## Guides

If you have made a guide of how to use the Kryptokrona SDK feel free to send a PR and add your link to a list below.

Official guide is coming soon.

## Contribute

If you would like to contribute to this project there is two ways:

- Send a pull request
- Donate to XKR address

### Pull Request

Read through the developer documentation at: [https://docs.kryptokrona.org/developer/kryptokrona-sdk](https://docs.kryptokrona.org/developer/kryptokrona-sdk).

We recommend using IntelliJ to work on this project.

We appreciate all contributions whether it be small changes such as documentation of source code to major improvement of code.
The easiest way is to make a fork and then make a pull request into our develop branch.

To make the PR go through make sure to include this information:

```
What does this PR do?

Why are these changes required?

This PR has been tested using (e.g. Unit Tests, Manual Testing):

Extra details?
```

NOTE: Remember to update existing diagrams if there is some bigger improvements of code so it's up to date with the implementation.

### Donate

XKR: SEKReXXU9aJPiwjX2XkpbK8ACMWbUNXcYPxUVSiUYpNdhj8Z2snEy8CjjorZUNyswQNfzAmVWuGksU72Sf3Kq79Zd3fJWHq4Nyx

## Contributors

The following contributors have either helped to start this project, have contributed
code, are actively maintaining it (including documentation), or in other ways
being awesome contributors to this project. **We'd like to take a moment to recognize them.**

[<img src="https://github.com/mjovanc.png?size=72" alt="mjovanc" width="72">](https://github.com/mjovanc)
[<img src="https://github.com/softx01.png?size=72" alt="softx01" width="72">](https://github.com/softx01)

## License

The 3-Clause BSD License.

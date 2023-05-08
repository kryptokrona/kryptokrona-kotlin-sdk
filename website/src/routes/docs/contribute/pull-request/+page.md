---
title: Pull Request
---

We highly recommend using IntelliJ to work on this project. Please make sure to install the plugins:

* [Detekt](https://plugins.jetbrains.com/plugin/10761-detekt)
* [Kotlin](https://plugins.jetbrains.com/plugin/6954-kotlin)

We appreciate all contributions whether it be small changes such as documentation of source code to major improvement of code.
The easiest way is to make a fork and then make a pull request into our develop branch.

**Before you create a pull request make sure to check the following:**

* You have not introduced circular dependencies in the project? For example, `kryptokrona-core` is using `kryptokrona-util`, and `kryptokrona-util` is using `kryptokrona-core`? 
* Have you run detekt (static code analysis) locally and fixed the errors?
* Have you run kover (code coverage) to check if it's at least above 60%?
* Have you run all unit tests and it passed?
* If you created new functionality, have you made unit test(s) to cover it?
* Have you read through [Branching and Merging](/docs/contributing/branching-and-merging)?

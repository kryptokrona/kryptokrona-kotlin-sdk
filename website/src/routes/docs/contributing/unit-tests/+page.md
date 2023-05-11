---
title: Unit Tests
---

:::tip[Quote]
Unit tests are the foundation of reliable software; they give you the freedom to evolve 
and improve without fear. - Unknown
:::

:::note[Note]
This page and guidelines are a work in progress. And some things might not be true to the
current implementation, but we are striving for it!
:::

We work very heavy with unit tests. We don't enforce TDD style of development, but it's recommended. 
Since there are a lot of different parts that need to work together and a lot of isolated code that is
used differently depending on who is using the SDK, we need to make sure that everything 
works as expected. We use [JUnit](https://junit.org/junit5/) for our unit tests.

Here are some guidelines for writing unit tests:

- Limit unit tests to testing an isolated single unit of code: function, class and/or module.
- Unit tests should not break when unrelated functions, classes or modules are changed.
- Use test driven development, when possible.
- Fast running unit tests help you to stay focused and confident in what you're doing.
- Ensure that edge cases are covered by the unit tests when it would otherwise be difficult, prohibitively time-consuming or require significantly more lines of code (higher technical debt) to test via integration tests.
- Use mocks to avoid turning your isolated tests into integration tests that depend on other modules.
- Recognize that unit tests don't prove that your code actually does what it's intended to do, they only prove that the code is internally consistent with itself and with the tests. Integration and end-to-end testing is more apt at validating that the code meets its objective by taking into account the entire system as a whole.
- Technical debt from too many tests is most apparent when refactoring APIs or making substantial changes to the code - you end up making every change several times, once in the implementation and a bunch of times in the tests. Keep your future self in the back of your mind when writing tests, don't make it unnecessarily hard to refactor. The more times you go through the cycle of writing tests and having to refactor them the more concise your test writing will get over time.
- Feeling uneasy that you are breaking stuff during refactoring is an indication that there isn't enough test coverage.
- Strive for 100% test coverage if it's easy and at least 90% if it's hard.
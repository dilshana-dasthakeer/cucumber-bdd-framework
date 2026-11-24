# Cucumber BDD Framework

[![CI](https://github.com/dilshana-dasthakeer/cucumber-bdd-framework/actions/workflows/ci.yml/badge.svg)](https://github.com/dilshana-dasthakeer/cucumber-bdd-framework/actions/workflows/ci.yml)
![Java 17](https://img.shields.io/badge/Java-17-orange)
![Cucumber 7](https://img.shields.io/badge/Cucumber-7.20-23D96C)
![Selenium 4](https://img.shields.io/badge/Selenium-4.35-43B02A)
![License: MIT](https://img.shields.io/badge/License-MIT-blue)

A BDD test framework in Java where business-readable scenarios drive Selenium tests. Requirements are written as Gherkin, step definitions stay thin, and page objects hold every locator.

It tests the public practice shop [Sauce Demo](https://www.saucedemo.com): sign in, browse and sort products, fill a cart and check out.

## What it shows

| Area | How it is done |
|---|---|
| **Gherkin** | Three feature files with `Background`, `Scenario Outline` and data tables. Scenarios describe shopper behaviour, with no clicks or locators in the wording. |
| **Layers** | Feature files, then step definitions, then page objects, then the driver. Steps never touch a locator, and pages never contain assertions. |
| **Dependency injection** | PicoContainer gives every scenario its own `TestContext`, so parallel scenarios never share a browser. |
| **Tags** | `@smoke` is a fast gate, `@regression` is the full suite. Choose a set with `-Dcucumber.filter.tags`. |
| **Parallel runs** | The TestNG runner runs scenarios in parallel (`-Dthreads=2` by default). |
| **Failure evidence** | A hook attaches a screenshot to the report whenever a scenario fails, then always closes the browser. |
| **Cross-browser** | Chrome and Firefox, headless by default. |
| **Reporting** | Cucumber HTML, JSON and JUnit reports in `target/cucumber-report`, uploaded as a CI artifact. |
| **CI** | GitHub Actions: smoke gate, then regression in a Chrome and Firefox matrix. Runs on every push, pull request and nightly. |

## Project layout

```
src/test/
  resources/features/        login.feature, catalogue.feature, checkout.feature
  java/com/dilshana/bdd/
    steps/                   step definitions (one class per area)
    pages/                   page objects (BasePage, LoginPage, InventoryPage, CartPage, CheckoutPage)
    support/                 DriverFactory, TestContext, Hooks
    runners/                 RunCucumberTest (TestNG + parallel scenarios)
```

## Run it

You need Java 17 and Maven. Browsers are matched to their drivers automatically by Selenium Manager.

```bash
mvn test                                          # everything, Chrome, headless
mvn test -Dcucumber.filter.tags="@smoke"          # smoke scenarios only
mvn test -Dbrowser=firefox                        # chrome | firefox
mvn test -Dheadless=false                         # watch the browser
mvn test -Dthreads=4                              # more parallel scenarios
mvn test -Dcucumber.filter.tags="@regression and not @smoke"
```

Open `target/cucumber-report/index.html` after a run.

## Example

```gherkin
@smoke @regression
Scenario: A shopper completes an order
  When the shopper starts checkout
  And the shopper enters the delivery details "Dilshana", "Dasthakeer" and "20000"
  And the shopper places the order
  Then the order confirmation says "Thank you for your order!"
```

## Adding a scenario

1. Write the scenario in a feature file using the shopper's wording.
2. Reuse an existing step, or add one to the matching class in `steps/`.
3. If the step needs a new screen action, add a method to the page object. Keep locators private to the page.

## Related

- [selenium-java-testng-framework](https://github.com/dilshana-dasthakeer/selenium-java-testng-framework): code-first Selenium and TestNG with Allure reporting
- [playwright-typescript-framework](https://github.com/dilshana-dasthakeer/playwright-typescript-framework): Playwright with TypeScript

## Licence

MIT

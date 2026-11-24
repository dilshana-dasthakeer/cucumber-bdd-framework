package com.dilshana.bdd.support;

import org.openqa.selenium.WebDriver;

/**
 * Shared state for one scenario. PicoContainer creates a new instance for every scenario
 * and hands the same instance to every step class, so parallel scenarios never share a browser.
 */
public class TestContext {

    private WebDriver driver;

    public WebDriver driver() {
        if (driver == null) {
            driver = DriverFactory.create();
        }
        return driver;
    }

    public boolean hasDriver() {
        return driver != null;
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

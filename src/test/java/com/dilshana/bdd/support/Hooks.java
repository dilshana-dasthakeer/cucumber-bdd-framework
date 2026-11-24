package com.dilshana.bdd.support;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/** Runs around every scenario: attaches a screenshot when it fails, then always closes the browser. */
public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && context.hasDriver()) {
                byte[] png = ((TakesScreenshot) context.driver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", "Screenshot of the failure");
            }
        } finally {
            context.quit();
        }
    }
}

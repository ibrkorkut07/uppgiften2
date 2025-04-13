package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utilities.Driver;
import utilities.TestUtils;

public class Hooks {
    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            TestUtils.takeScreenshot(Driver.getDriver(), "FAILED_" + scenario.getName());
        }
        Driver.quitDriver();
    }
}
package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utilities.Driver;
import utilities.ScreenshotUtils;

public class Hooks {
    private final WebDriver driver = Driver.getDriver();

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver,
                    scenario.getName().replace(" ", "_"));
            scenario.attach(ScreenshotUtils.takeScreenshotAsByte(driver),
                    "image/png", scenario.getName());
        }
    }

    @After
    public void tearDown() {
        Driver.quitDriver();
    }
}
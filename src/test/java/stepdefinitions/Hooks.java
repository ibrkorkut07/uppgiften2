package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

import java.util.Optional;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        String browser = getBrowserFromTag(scenario);
        driver = DriverFactory.createDriver(browser);
        if (driver == null) {
            throw new IllegalStateException("WebDriver could not be initialized");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if test failed
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failed_test");
        }

        // Close browser
        if (driver != null) {
            driver.quit();
        }
    }

    private String getBrowserFromTag(Scenario scenario) {
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.equalsIgnoreCase("@chrome")) return "chrome";
            if (tag.equalsIgnoreCase("@firefox")) return "firefox";
            if (tag.equalsIgnoreCase("@edge")) return "edge";
            if (tag.equalsIgnoreCase("@safari")) return "safari";
        }
        return "chrome"; // default browser
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call setUp() first.");
        }
        return driver;
    }
}
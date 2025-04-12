package stepdefinitions;  // Same package as step definitions

// Import required Cucumber and Selenium classes
import io.cucumber.java.After;  // After scenario hook
import io.cucumber.java.AfterStep;  // After each step hook
import io.cucumber.java.Scenario;  // Represents test scenario
import org.openqa.selenium.WebDriver;  // Main Selenium interface
import utilities.Driver;  // Custom driver management utility
import utilities.ScreenshotUtils;  // Custom screenshot utility

public class HooksEXP {
    // Gets shared WebDriver instance from Driver utility
    private final WebDriver driver = Driver.getDriver();

    // Executes after EACH step in a scenario
    @AfterStep
    public void afterStep(Scenario scenario) {
        // Only take screenshot if scenario failed
        if (scenario.isFailed()) {
            // Attaches screenshot to Cucumber report
            scenario.attach(
                    ScreenshotUtils.takeScreenshotAsByte(driver),  // Takes screenshot as bytes
                    "image/png",  // Specifies image format
                    scenario.getName()  // Uses scenario name as identifier
            );
        }
    }

    // Executes after EACH scenario
    @After
    public void tearDown() {
        // Quits the browser and cleans up driver instance
        Driver.quitDriver();
    }
}
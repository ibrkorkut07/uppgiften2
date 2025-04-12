package utilities;// Alternative test runner with minimal configuration
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)  // Basic Cucumber-JUnit integration
@CucumberOptions(
        // Basic reporting plugins
        plugin = {
                "pretty",  // Console formatting
                "html:target/cucumber-reports/cucumber.html",  // HTML report
                "json:target/cucumber-reports/cucumber.json"  // JSON report
        },

        // Feature files location
        features = "src/test/resources/features",

        // Packages to scan for glue code
        glue = {
                "stepdefinitions",  // Step definitions
                "runners",  // Hooks
                "utilities"  // Helper classes
        }
)
public class RunCucumberTestEXP {
    // Simple alternative to TestRunner.java
    // Useful for different configurations or environments
}
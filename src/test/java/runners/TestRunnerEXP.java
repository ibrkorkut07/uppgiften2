package runners;  // Organizes runner classes

// Import required testing classes
import io.cucumber.junit.Cucumber;  // Cucumber-JUnit integration
import io.cucumber.junit.CucumberOptions;  // Configuration options
import org.junit.runner.RunWith;  // JUnit annotation to run with Cucumber

@RunWith(Cucumber.class)  // Tells JUnit to run with Cucumber
@CucumberOptions(
        // REPORTING PLUGINS:
        plugin = {
                "pretty",  // Formats console output nicely

                // Generates HTML report in target folder
                "html:target/cucumber-reports/cucumber-html-report.html",

                // JSON report for CI tools like Jenkins
                "json:target/cucumber-reports/cucumber.json",

                // JUnit XML report for CI integration
                "junit:target/cucumber-reports/cucumber.xml",

                // Timeline report showing parallel execution
                "timeline:target/cucumber-reports/timeline",

                // Enhanced pretty report with more details
                "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports/pretty"
        },

        // Points to feature files location
        features = "src/test/resources/features",

        // Points to step definitions package
        glue = "stepdefinitions",

        // Makes console output more readable
        monochrome = true,

        // Publishes report to Cucumber's cloud service
        publish = true,

        // Only runs scenarios with both @firefox AND @chrome tags
        tags = "@firefox and @chrome"
)
public class TestRunnerEXP {
    // This empty class serves as entry point for Cucumber tests
    // All configuration is done through annotations
}
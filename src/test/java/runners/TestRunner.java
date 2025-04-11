package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",               // Enhanced HTML report
                "json:target/cucumber-reports/cucumber.json",                           // JSON for CI/CD & custom reports
                "junit:target/cucumber-reports/cucumber.xml",                           // JUnit format for Jenkins etc.
                "timeline:target/cucumber-reports/timeline",                            // Timeline report
                "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports/pretty"   // Pretty report
        },
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        monochrome = true,                      // Cleaner console output
        publish = true,                         // Publishes report to reports.cucumber.io
        tags = "@firefox and @chrome"           // Default tag filter
)
public class TestRunner {}

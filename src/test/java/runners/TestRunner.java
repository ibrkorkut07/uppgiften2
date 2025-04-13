package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "timeline:target/cucumber-reports/timeline",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports/pretty"
        },
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        monochrome = true,
        publish = true,
        tags = "@firefox and @chrome"
)
public class TestRunner {}

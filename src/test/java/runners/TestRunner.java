package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "junit:target/junit-reports/Cucumber.xml"
        },
        features = "src/test/resources/features"
)
public class TestRunner {}
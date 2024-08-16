package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        plugin="json:target/JsonReports/cucumber-report.json",
        glue = "StepDefinition"
      // tags= "@DeletePlace"
)

public class TestRunner {
}

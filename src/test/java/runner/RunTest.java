package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"json:target/cucumberreports.json" }, features = "src\\test\\resources\\features", glue = "stepdefinition", tags = {"@Blizz",
				"@Search " }, monochrome = false)

public class RunTest {

}


package CucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(dryRun=false,
features= {"src/test/java/feature/"},
glue= "StepDefinition"
)
public class TestRunner extends AbstractTestNGCucumberTests{

}

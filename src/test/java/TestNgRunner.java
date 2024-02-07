import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/cucumber",glue = "stepdefinition",monochrome = true,plugin = {"html:target/cucumber.html"},tags = "@Regression")
public class TestNgRunner extends AbstractTestNGCucumberTests {


}

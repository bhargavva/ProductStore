package productStore.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"classpath:productStore"},
                glue = {"productStore.stepDefinitions"},
                tags = "@buying-process",
                plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json"},
                monochrome = false,
                dryRun = false)
public class MainRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

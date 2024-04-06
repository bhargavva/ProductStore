package productStore.stepDefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static productStore.driver.DriverFactory.cleanUpDriver;
import static productStore.driver.DriverFactory.getDriver;

public class Hooks {

    @Before
    public void setUp() {
        getDriver();
    }

    @After
    public void tearDown() {
        cleanUpDriver();
    }
}

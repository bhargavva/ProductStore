package productStore.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import productStore.driver.DriverFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Base_PO {


    public WebDriver getDriver() {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void navigateToURL(String url){
        getDriver().get(url);
    }

    public void sendKeys(By by, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    public void sendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }

    public void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitForWebElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public WebElement findTheElement(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }
}

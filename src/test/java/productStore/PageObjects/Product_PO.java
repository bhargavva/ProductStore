package productStore.PageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class Product_PO extends Base_PO{

    @FindBy(xpath = "//a[normalize-space()='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(tagName = "h2")
    private WebElement productTitle;

    public Product_PO() {
        super();
    }

    public void addProductToCart(){
        waitForWebElementAndClick(addToCartButton);
    }

    public boolean isOnProductPage(String productName){
        return findTheElement(productTitle).getText().equals(productName);
    }

    public boolean confirmAlertIfPresent(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // wait for up to 10 seconds
        wait.until(ExpectedConditions.alertIsPresent());

        //Check for the alert
        Alert alert = getDriver().switchTo().alert();
        boolean alertPresent= alert.getText().equals("Product added");
        alert.accept();
        return alertPresent;
    }

}

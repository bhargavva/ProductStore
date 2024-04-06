package productStore.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import productStore.PageObjects.Base_PO;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.*;
import static productStore.driver.DriverFactory.getDriver;


public class BuyingProcess extends Base_PO {

    private WebDriver driver;

    public BuyingProcess() {
        this.driver = getDriver();
    }

    @Given("I am on Product Store Home Page and can view list of products")
    public void i_am_on_product_store_home_page_and_can_view_list_of_products() {
        //Loads the product store website
        navigateToURL("https://www.demoblaze.com/index.html");

        //Checking if the products list displayed on the Products Store website.
        WebElement productsList = findTheElement(By.xpath("//div[@id='tbodyid']"));
        List<WebElement> productListItems = productsList.findElements(By.xpath("./*")); // Selects all child elements

        // Assert that the products list is displayed and not empty
        assertTrue("Products list is not displayed on the homepage",
                productsList.isDisplayed());
        assertTrue("No products found in the list", !productListItems.isEmpty());
    }

    @When("I select a product to view details")
    public void i_select_a_product_to_view_details() {

        //Select a product and click on it
        waitForWebElementAndClick(By.xpath("//a[normalize-space()='Samsung galaxy s6']"));

        //Check if driver navigated on to the product details page.
        boolean isOnPrductPage = driver.getCurrentUrl().equals("https://www.demoblaze.com/prod.html?idp_=1");
        assertTrue("Failed to navigate to product details page", isOnPrductPage);
    }

    @And("I add product to cart")
    public void i_add_product_to_cart() throws InterruptedException {

        // Find the add to Card Button and click on it.
        waitForWebElementAndClick(By.xpath("//a[normalize-space()='Add to cart']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // wait for up to 10 seconds
        wait.until(ExpectedConditions.alertIsPresent());

        // Check if we get the popup alert message and confirm the alert
        Alert alert = driver.switchTo().alert();
        boolean isAlertDisplayed = alert.getText().equals("Product added");
        assertTrue("Unable to Confirm the Alert", isAlertDisplayed);
        alert.accept();
    }

    @When("I find my selected products in cart")
    public void i_find_my_selected_products_in_cart() {

        // Find the cart element and click on it to visit the cart page.
        waitForWebElementAndClick(By.xpath("//a[@id='cartur']"));

        // Verify if we successfully navigated to the cart page.
        boolean isOnCartPage = driver.getCurrentUrl().equals("https://www.demoblaze.com/cart.html");
        assertTrue("Failed to navigate to cart page", isOnCartPage);

        // Verify the product Selected.
        boolean isRightProdSelected = findTheElement(By.xpath("//td[normalize-space()='Samsung galaxy s6']")).getText().equals("Samsung galaxy s6");
        assertTrue("Correct Product not added to cart", isRightProdSelected);

    }

    @Then("I check out and buy the product")
    public void i_check_out_and_buy_the_product() throws InterruptedException {
        // Check out to place the order
        waitForWebElementAndClick(By.xpath("//button[normalize-space()='Place Order']"));

        // Check if the billing address form is displayed
        boolean isFormDisplayed = findTheElement(By.id("orderModal")).getCssValue("display").equals("block");
        assertTrue("The form element is not displayed", isFormDisplayed);

        //Fill the form with billing address and submit
        sendKeys(By.xpath("//input[@id='name']"),"Bhargav AV"); // Enter Name
        sendKeys(By.xpath("//input[@id='country']"), "India"); // Enter Country
        sendKeys(By.xpath("//input[@id='city']"), "Bengaluru"); // Enter City
        sendKeys(By.xpath("//input[@id='card']"), "4311 7654 9876 2348"); // Enter Credit Card Number
        sendKeys(By.xpath("//input[@id='month']"), "April"); // Enter Month
        sendKeys(By.xpath("//input[@id='year']"), "2024"); // Enter Year
        waitForWebElementAndClick(By.xpath("//button[normalize-space()='Purchase']")); // place order.
    }

    @And("I get order placed successfully message")
    public void i_get_order_placed_successfully_message() throws InterruptedException {
        //Check if we get the order confirmation message.
        assertTrue("We haven't received the confirmation alert after the purchase",
                findTheElement(By.xpath("//div[contains(@class,'showSweetAlert visible')]")).isDisplayed());

        //Acknowledge the order confirmation
        waitForWebElementAndClick(By.xpath("//button[normalize-space()='OK']"));

        // Confirm if we came back to home page automatically after placing the order.
        boolean isOnHomePage = driver.getCurrentUrl().equals("https://www.demoblaze.com/index.html");
        assertTrue("System failed navigating back to home page", isOnHomePage);
    }
}

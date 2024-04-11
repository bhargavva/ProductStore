package productStore.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import productStore.PageObjects.*;
import static org.testng.AssertJUnit.*;


public class BuyingProcess extends Base_PO {

    protected Cart_PO cartPo;
    protected Product_PO productPo;
    protected NavBar_PO navBarPo;
    protected Home_PO homePo;
    protected CheckOutForm_PO checkOutFormPo;

    public BuyingProcess(Cart_PO cartPo, Home_PO homePo, NavBar_PO navBarPo, Product_PO productPo, CheckOutForm_PO checkOutFormPo){
        this.cartPo = cartPo;
        this.homePo = homePo;
        this.navBarPo = navBarPo;
        this.productPo = productPo;
        this.checkOutFormPo = checkOutFormPo;
    }

    @Given("I am on Product Store Home Page and can view list of products")
    public void i_am_on_product_store_home_page_and_can_view_list_of_products() {

        //Loads the product store website
        homePo.navigateToHomePage();
        //Verify of the product list displayed
        assertTrue("No products found in the list", homePo.isProductListDisplayed());
    }

    @When("I select a product to view details")
    public void i_select_a_product_to_view_details() {

        //Select a product and click on it
        homePo.navigateToProductPage("Nokia lumia 1520");
        //Check if driver navigated on to the product details page.
        assertTrue("Failed to navigate to product details page",
                productPo.isOnProductPage("Nokia lumia 1520"));
    }

    @And("I add product to cart")
    public void i_add_product_to_cart() throws InterruptedException {

        // Find the add to Card Button and click on it.
        productPo.addProductToCart();
        // Confirm the alert message
        assertTrue("Unable to verify if product added to cart",
                productPo.confirmAlertIfPresent());
    }

    @When("I find my selected products in cart")
    public void i_find_my_selected_products_in_cart() {

        // navigate to cart page from Navigation bar
        navBarPo.navigateToCartPage();
        // Verify if we successfully navigated to the cart page.
        assertTrue("Failed to navigate to cart page", cartPo.isOnCartPage());
        // Verify the product Selected.
        assertTrue("Filed to add selected product to Cart",
                cartPo.isSelectedItemInCart("Nokia lumia 1520"));
    }

    @Then("I check out and buy the product")
    public void i_check_out_and_buy_the_product() {

        // Check out to place the order
        cartPo.placeOrder();
        // Check if the billing address form is displayed
        assertTrue("The form element is not displayed", checkOutFormPo.isCheckOutFormVisible());
        //Fill the form with billing address and complete purchase
        checkOutFormPo.enterBillingAddress("Bhargav AV", "India", "Bengaluru",
                "2345 5678 9876 4456", "April", "2024");
        checkOutFormPo.completePurchase();
    }

    @And("I get order placed successfully message")
    public void i_get_order_placed_successfully_message() {

        //Check if we get the order confirmation message.
        assertTrue("Failed to receive order confirmation", checkOutFormPo.isPurchaseConfirmed());
        //Acknowledge the order confirmation
        checkOutFormPo.acceptOrderConfirmAlert();
        // Confirm if we came back to home page automatically after placing the order.
        assertTrue("System failed navigating back to home page", homePo.isOnHomePage());
    }
}

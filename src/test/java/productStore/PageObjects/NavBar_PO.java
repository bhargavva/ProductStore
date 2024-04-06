package productStore.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBar_PO extends Base_PO{

    @FindBy(xpath = "//li[@class='nav-item active']//a[@class='nav-link']")
    private WebElement homeLink;

    @FindBy(xpath = "//a[normalize-space()='Contact']")
    private WebElement contactFormLink;

    @FindBy(xpath = "//a[normalize-space()='About us']")
    private WebElement aboutUsLink;

    @FindBy(xpath = "//a[@id='cartur']")
    private WebElement cartPageLink;

    @FindBy(xpath = "//a[@id='login2']")
    private WebElement loginFormLink;

    @FindBy(xpath = "//a[@id='signin2']")
    private WebElement signUpFormLink;

    public NavBar_PO() {
        super();
    }
    public void navigateToHomeViaHomeLinkInNavbar(){
        waitForWebElementAndClick(homeLink);
    }

    public void navigateToCartPage(){
        waitForWebElementAndClick(cartPageLink);
    }

}

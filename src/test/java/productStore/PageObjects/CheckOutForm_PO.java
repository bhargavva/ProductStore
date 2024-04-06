package productStore.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutForm_PO extends Base_PO{
    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameTextBox;
    @FindBy(xpath = "//input[@id='country']")
    private WebElement countryTextBox;
    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityTextBox;
    @FindBy(xpath = "//input[@id='card']")
    private WebElement creditCardNumTextBox;
    @FindBy(xpath = "//input[@id='month']")
    private WebElement monthTextBox;
    @FindBy(xpath = "//input[@id='year']")
    private WebElement yearTextBox;
    @FindBy(xpath = "//button[normalize-space()='Purchase']")
    private WebElement purchaseButton;
    @FindBy(xpath = "//div[@id='orderModal']//button[@type='button'][normalize-space()='Close']")
    private WebElement cancelButton;
    @FindBy(id = "orderModal")
    private WebElement formElement;
    @FindBy(xpath = "//div[contains(@class,'showSweetAlert visible')]")
    private WebElement confirmationAlert;

    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement okButton;

    //Constructor
    public CheckOutForm_PO() {
        super();
    }

    public boolean isCheckOutFormVisible(){
        return findTheElement(formElement).getCssValue("display").equals("block");
    }

    public void enterBillingAddress(String name, String country, String city, String creditCardNum, String month, String year){
        //Fill the form with billing address and submit
        sendKeys(nameTextBox, name); // Enter Name
        sendKeys(countryTextBox, country); // Enter Country
        sendKeys(cityTextBox, city); // Enter City
        sendKeys(creditCardNumTextBox, creditCardNum); // Enter Credit Card Number
        sendKeys(monthTextBox, month); // Enter Month
        sendKeys(yearTextBox, year); // Enter Year

    }
    public void completePurchase(){
        waitForWebElementAndClick(purchaseButton);
    }

    public boolean isPurchaseConfirmed(){
       return confirmationAlert.isDisplayed();
    }

    public void acceptOrderConfirmAlert(){
        waitForWebElementAndClick(okButton);
    }
}

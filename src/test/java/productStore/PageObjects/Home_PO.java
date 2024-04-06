package productStore.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home_PO extends Base_PO{

    @FindBy(xpath = "//a[@id='cat']")
    private WebElement categoriesLinkElement;

    @FindBy(xpath = "//a[@id='nava']")
    private WebElement productStoreLogo;

    @FindBy(xpath = "//div[@id='tbodyid']")
    private WebElement productListElement;

    public Home_PO() {
        super();
    }

    public void navigateToHomePage(){
        getDriver().get("https://www.demoblaze.com/index.html");
    }

    public void navigateToHomePageViaCategories(){
        waitForWebElementAndClick(categoriesLinkElement);
    }

    public void navigateToHomePageviaLogo(){
        waitForWebElementAndClick(productStoreLogo);
    }

    public boolean isProductListDisplayed(){
        return productListElement.findElement(By.xpath("./*")).isDisplayed();
    }

    public void navigateToProductPage(String productName){
        waitForWebElementAndClick(By.xpath("//a[normalize-space()='"+productName+"']"));
    }

    public boolean isOnHomePage(){
        return getDriver().getCurrentUrl().equals("https://www.demoblaze.com/index.html");
    }
}

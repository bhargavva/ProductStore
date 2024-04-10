package productStore.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart_PO extends Base_PO {

    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement cartContainer;

    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    private WebElement placeOrderButton;

    public Cart_PO() {
        super();
    }

    public void placeOrder() {
        waitForWebElementAndClick(placeOrderButton);
    }

    public boolean isOnCartPage() {
        return getDriver().getCurrentUrl().equals("https://www.demoblaze.com/cart.html");
    }

    public boolean isSelectedItemInCart(String productName) {
        WebElement productSelected = cartContainer.findElement(By.xpath("//td[normalize-space()='"+productName+"']"));
        return productSelected.getText().equals(productName);
    }
}

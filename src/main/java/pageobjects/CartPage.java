package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage  extends AbstractComponent{
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css =".cartSection h3" )
    List<WebElement> products;

    @FindBy(css ="li[class='totalRow'] button[type='button']" )
    WebElement checkout;


public boolean getProductDisplayed(String productName){
    Boolean match =products.stream().anyMatch(product->product.getText().equals(productName));
    return match;

}

public void goToCheckout(){
    checkout.click();
}

}

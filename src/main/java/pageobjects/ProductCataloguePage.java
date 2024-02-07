package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProductCataloguePage extends AbstractComponent{

   public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
       PageFactory.initElements(driver,this);
    }

    @FindBy(css =".mb-3" )
    List<WebElement> products;

   By productBy = By.cssSelector(".mb-3");

   By toastMessage= By.cssSelector("#toast-container");
   By addToCart= By.cssSelector("button:last-child");

   public List<WebElement> getProductList(){
       waitForElementToAppear(productBy);
       return products;

   }

   public String chooseProductAddToCart(String productName){
       WebElement reqProduct = getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
       reqProduct.findElement(addToCart).click();
       waitForElementToAppear(toastMessage);
       return driver.findElement(toastMessage).getText();


   }


}

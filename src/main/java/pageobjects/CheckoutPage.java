package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends AbstractComponent{
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    @FindBy(css ="input[placeholder='Select Country']" )
    WebElement countries;

    @FindBy(css = ".btnn.action__submit.ng-star-inserted")
    WebElement submit;


    By countryBy=By.cssSelector("button.ng-star-inserted:nth-child(3)");


    public void selectCountry(String country){
        Actions a = new Actions(driver);
        a.sendKeys(countries,country).build().perform();
        waitForElementToAppear(countryBy);
        driver.findElement(countryBy).click();

    }
    public void submitOrder(){
        submit.click();
    }

}

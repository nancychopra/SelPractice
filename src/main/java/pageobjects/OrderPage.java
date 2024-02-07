package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends AbstractComponent{
    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    By confirmationMessage = By.cssSelector(".hero-primary");

    public String getConfirmation(){

        waitForElementToAppear(confirmationMessage);
        return driver.findElement(confirmationMessage).getText();

    }


}

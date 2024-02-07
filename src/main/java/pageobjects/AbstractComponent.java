package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(css = ".btn.btn-custom[routerlink='/dashboard/cart']")
    WebElement cart;

    AbstractComponent(WebDriver driver){
        this.driver= driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));

    }

    public void waitForElementToAppear(By findBy){
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void goToCart(){
        cart.click();
    }

}

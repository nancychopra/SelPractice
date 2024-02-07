package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  extends AbstractComponent{

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        if(!driver.getTitle().equals("Let's Shop")){
            throw new IllegalStateException("This is not the sign in Page " + driver.getCurrentUrl() +" is not the right url");
        }

    }

    @FindBy(css = "#userEmail")
    WebElement userEmail;

    @FindBy(css = "#userPassword")
    WebElement userPassword;

    @FindBy(css = "#login")
    WebElement login;

    @FindBy(css = "[class*='toast-error']")
    WebElement toastMessage;


    public void login(String userName, String password) throws InterruptedException {
        userEmail.sendKeys(userName);
        userPassword.sendKeys(password);
        login.click();
        System.out.println("Login button clicked");
        Thread.sleep(1000);
    }

    public String getError(){
        waitForElementToAppear(toastMessage);
        return toastMessage.getText();
    }


}

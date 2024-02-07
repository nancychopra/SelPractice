package designpatterns.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleEnglish extends GooglePage{

    protected WebDriver driver;
    protected WebDriverWait wait;


    @FindBy(css = "[name='q']")
    protected WebElement search;

    @FindBy(css="[name='btnK']")
    private WebElement btn;

    @FindBy(css="#search div.MjjYud")
    private List<WebElement> results;

    public GoogleEnglish(final WebDriver driver){
        this.driver= driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
    }
    @Override
    public void launch() {
        driver.get("https://www.google.com");

    }

    @Override
    public void search(String keyword) {
        search.sendKeys(keyword);
        wait.until((d)->btn.isDisplayed());
        btn.click();
    }

    @Override
    public int results() {
        wait.until((d)->results.size()>1);
        return results.size();
    }


}

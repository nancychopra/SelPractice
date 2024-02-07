package designpatterns.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleFrench extends GoogleEnglish{
    public GoogleFrench(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void launch(){
        driver.get("https://www.google.fr");
    }
}

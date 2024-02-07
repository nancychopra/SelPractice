package designpatterns.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleArabic extends GoogleEnglish{


    @FindBy(xpath ="//span[@class='ly0Ckb']" )
    private WebElement language;

    @FindBy(name ="btnI" )
    private WebElement button;



    public GoogleArabic(WebDriver driver){
        super( driver);
        PageFactory.initElements(driver,this);
    }

    public void launch(){
        this.driver.get("https://www.google.com/?hl=ar");
        this.language.click();
    }

    public void search(String keyword){
        this.wait.until((d)-> {
            return language.isDisplayed();
        });
        language.click();
        super.search(keyword);
    }

}

package NewFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.Credentials;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v115.log.Log;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.Optional;
import java.util.function.Predicate;

public class Logs {


    @Test
    public void consoleLogs(){

        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        //listeners - OnTestFailure

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
        LogEntries entries= driver.manage().logs().get(LogType.BROWSER);//Get console logs

        for(LogEntry entry: entries){
            System.out.println(entry.getMessage());
            System.out.println(entry.getTimestamp());
        }


    }


    @Test
    public void basicAuth(){

        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
        ChromeDriver driver = new ChromeDriver();

//        Predicate<URI> whenThisMatches = new Predicate<URI>() {
//            @Override
//            public boolean test(URI uri) {
//                return uri.getHost().contains("httpbin.org");
//            }
//        };

        Predicate<URI> whenThisMatches = uri -> uri.getHost().contains("httpbin.org");
        ((HasAuthentication)driver).register(whenThisMatches, UsernameAndPassword.of("foo", "bar"));
        driver.get("http://httpbin.org/basic-auth/foo/bar");


    }
}

package test.example;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SeleniumRemote {

    @Test
    public void googleTest() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps= new DesiredCapabilities();
        URL url = new URL("http://43.204.38.251:4444/wd/hub");
        caps.setPlatform(Platform.LINUX);
        //caps.setBrowserName("MicrosoftEdge");
        caps.setBrowserName("chrome");


        caps.setVersion("120.0");
        WebDriver driver = new RemoteWebDriver(url,caps);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Rahul"));
        Thread.sleep(1000);

        driver.quit();


    }
}

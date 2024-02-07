package test.example;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestDemo {

    @Test
    public void titleCheck() throws MalformedURLException
    {

        //WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.139:4444"), caps);
        MutableCapabilities caps = new MutableCapabilities();

        WebDriver driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com");
        Assert.assertTrue(driver.getTitle().contains("Rahul"));




    }
}


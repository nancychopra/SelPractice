package org.example;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DockerDemo {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");

        FirefoxOptions options = new FirefoxOptions();
//        cap.setBrowserName(String.valueOf(Browser.CHROME));
//        cap.setPlatform(Platform.LINUX);
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        driver.get("https://google.co.in");
        System.out.println(driver.getTitle());
        Thread.sleep(10000);
        driver.quit();



    }
}

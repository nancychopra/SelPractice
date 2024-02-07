package e2e.tests;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;

public class AutoIt {

   // @Test
    public void testWindow(){

//        ChromeOptions options = new ChromeOptions();
//        options.setAcceptInsecureCerts(true);
//        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
//        WebDriver driver = new ChromeDriver(options);
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/");

       //driver.findElement(By.cssSelector("a[href='/basic_auth']")).click();

        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

    }

    public void uploadFile(){

        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        File file = new File("./pom.xml");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(file.getAbsolutePath());


    }

    //@Test
    public void downloadFile(){

        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        HashMap<String,Object> pref =  new HashMap<>();
        pref.put("download.prompt_for_download",false);
        options.setExperimentalOption("prefs",pref);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        ChromeDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://filesamples.com/formats/bat");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("body > div:nth-child(2) > main:nth-child(2) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(3) > span:nth-child(2)")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void gridTest() throws MalformedURLException {


        DesiredCapabilities caps = new DesiredCapabilities();
        URL url = new URL("http://localhost:4444");
        caps.setBrowserName("firefox");
        caps.setVersion("115");
        caps.setPlatform(Platform.MAC);
        RemoteWebDriver driver = new RemoteWebDriver(url,caps);

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/locatorspractice");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(driver.getTitle());
        driver.close();

    }







}

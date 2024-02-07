package NewFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.emulation.Emulation;
import org.openqa.selenium.devtools.v115.performance.Performance;
import org.openqa.selenium.devtools.v115.performance.model.Metric;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MobileEmulatorTest {
    ChromeDriver driver;
    DevTools tools;

    @BeforeMethod
    public void setup(){
        System.out.println("Running before Test");
        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        tools = driver.getDevTools();
        tools.createSession();


    }



    public void emulate() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
//        ChromeDriver driver = new ChromeDriver();
//
//        DevTools tools = driver.getDevTools();
//        tools.createSession();
//        tools.send(Emulation.setDeviceMetricsOverride(600,1000,50,true, Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()
//        ,Optional.empty()));
        Map<String,Object> deviceMetrics = new HashMap();
        deviceMetrics.put("width",500);
        deviceMetrics.put("height",900);
        deviceMetrics.put("deviceScaleFactor",50);
        deviceMetrics.put("mobile",true);
        //execute CDP command when custom command is not available
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Library")).click();
        driver.close();

    }

    @Test
    public void coordinates() throws InterruptedException {

        Map<String,Object>coordinates = new HashMap<String,Object>();

        coordinates.put("latitude", 40);
        coordinates.put("longitude", 3);
        coordinates.put("accuracy", 1);
        tools.send(Emulation.setGeolocationOverride(Optional.of(40), Optional.of(3), Optional.of(1)));

        //driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("https://my-location.org/");
//        driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
//        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
//        String title =driver.findElement(By.cssSelector(".our-story-card-title")).getText();
//        System.out.println(title);



    }

    @Test
    public void perfmetrics()
    {
        tools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = tools.send(Performance.getMetrics());

        driver.get("https://google.com");
        driver.quit();

        for(Metric m : metricList) {
            System.out.println(m.getName() + " " + m.getValue());
        }


        }

}

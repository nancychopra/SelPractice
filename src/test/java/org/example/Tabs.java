package org.example;

import e2e.testcomponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Tabs extends BaseTest {


    public void links() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        System.out.println(driver.findElement(By.cssSelector("#gf-BIG")).findElements(By.tagName("a")).size());

        WebElement footer =driver.findElement(By.xpath("//div[@id='gf-BIG']/table/tbody/tr/td/ul/li/h3/a[text()='Discount Coupons']/ancestor::ul"));
        String openInANewTab= Keys.chord(Keys.COMMAND,Keys.ENTER);

        for (int i = 0; i <footer.findElements(By.xpath(".//a")).size() ; i++) {
            footer.findElements(By.xpath(".//a")).get(i).sendKeys(openInANewTab);

        }
        Thread.sleep(5);

        Set<String> windows=driver.getWindowHandles();
        Iterator<String> it= windows.iterator();
        while(it.hasNext()){
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());

        }


    }


    public void dates(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.path2usa.com/travel-companions");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#form-field-travel_comp_date")));
        driver.findElement(By.cssSelector("#form-field-travel_comp_date")).click();
        for (int i = 0; i <driver.findElements(By.cssSelector(".flatpickr-day")).size() ; i++) {
            String date =driver.findElements(By.cssSelector(".flatpickr-day")).get(i).getText();
            if(date.equalsIgnoreCase("23"))
                driver.findElements(By.cssSelector(".flatpickr-day")).get(i).click();

        }


    }


    public void js(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://stackoverflow.com/questions/14834198/table-scroll-with-html-and-css");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(100,1200)");
        js.executeScript("document.querySelector('div[id=\"answer-14834307\"] pre[class=\"snippet-code-html lang-html s-code-block\"]').scrollTop=5000");
        //driver.close();

    }


    public void grid(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.freecodecamp.org/news/html-tables-table-tutorial-with-css-example-code/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        List<WebElement> list=driver.findElements(By.cssSelector("table td:nth-child(3)"));
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i).getText());

        }


        //driver.close();

    }


    public void sslCertAndScreenshot() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");

        TakesScreenshot sc = (TakesScreenshot) driver;
        File src=sc.getScreenshotAs(OutputType.FILE);


        FileUtils.copyFile(src,new File("/Users/nancychopra/workspace/Selenium/sslCertAndScreenshot/screenshot.png" ));
        driver.manage().deleteAllCookies();
        SoftAssert s = new SoftAssert();

        s.assertTrue(true);
        s.assertEquals(1,2);


        driver.manage().window().maximize();
        s.assertAll();
        driver.close();

    }

    @Test(timeOut = 4000  )
    public void testBrokenLink() throws IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        List<WebElement> list=driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        SoftAssert a= new SoftAssert();

        for (WebElement e :list) {
            String url = e.getAttribute("href");
            System.out.println(url);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            con.connect();
            int code =con.getResponseCode();
            if(code>400){
                a.assertTrue(false,"Broken link at " + e.getText() + " Response code is " + code);

            }
        }
        a.assertAll();




        driver.close();


    }




}

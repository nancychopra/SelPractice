package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;



import java.time.Duration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {

        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver");
        System.setProperty("webdriver.gecko.driver","/Users/nancychopra/Software Downloads/geckodriver");

        //WebDriver fDriver= new ChromeDriver();
        WebDriver fDriver= new FirefoxDriver();
        fDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        fDriver.manage().window().maximize();
        System.out.println(fDriver.manage().window().getSize());

        fDriver.get("https://rahulshettyacademy.com/locatorspractice");
        System.out.println("URL " + fDriver.getCurrentUrl());
        System.out.println("Title " + fDriver.getTitle());
        fDriver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
        fDriver.findElement(By.name("inputPassword")).sendKeys("hellow123");
        fDriver.findElement(By.cssSelector("button[type='submit']")).click();
        System.out.println(fDriver.findElement(By.cssSelector("p.error")).getText());
        Assert.assertEquals(fDriver.findElement(By.cssSelector("p.error")).getText(),"* Incorrect username or password");

        fDriver.quit();

        String s = new String("Nancy:Chopra");
        String s1[]=s.split(":");
        for (String s2 :s1)
            System.out.println(s2);

// All locators in selenium
//        public class Locators {
//
//
//
//            public static void main(String[] args) throws InterruptedException {
//
//// TODO Auto-generated method stub
//
////implicit wait - 2 seconds time out
//
//                System.setProperty("webdriver.chrome.driver", "/Users/rahulshetty/Documents/chromedriver");
//
//                WebDriver driver = new ChromeDriver();
//
//                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//                driver.get("https://rahulshettyacademy.com/locatorspractice/");
//
//                driver.findElement(By.id("inputUsername")).sendKeys("rahul");
//
//                driver.findElement(By.name("inputPassword")).sendKeys("hello123");
//
//                driver.findElement(By.className("signInBtn")).click();
//
//                System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
//
//                driver.findElement(By.linkText("Forgot your password?")).click();
//
//                Thread.sleep(1000);//
//
//                driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");
//
//                driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");
//
//                driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
//
//                driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@gmail.com");
//
//                driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");
//
//                driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
//
//                System.out.println(driver.findElement(By.cssSelector("form p")).getText());
//
//                driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
//
//                Thread.sleep(1000);
//
//                driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
//
//                driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
//
//                driver.findElement(By.id("chkboxOne")).click();
//
//                driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
//
//            }
//
//
//
//        }





    }
}

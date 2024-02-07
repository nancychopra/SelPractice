package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class ActionsDemo {


    public void demo(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick();
        a.moveToElement(driver.findElement(By.cssSelector("#nav-link-accountList"))).build().perform();

        driver.close();

    }


    public void windows(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.cssSelector(".blinkingText")).click();
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> it =windows.iterator();
        String parent = it.next();
        String child = it.next();
        driver.switchTo().window(child);
        String emailPara=driver.findElement(By.cssSelector(".im-para.red")).getText();
        String email = emailPara.split("at")[1].trim().split(" ")[0];
        driver.switchTo().window(parent);
        driver.findElement(By.cssSelector("#username")).sendKeys(email);


    }

    @Test
    public void frame(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        //driver.switchTo().frame(0);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
        WebElement draggable = driver.findElement(By.cssSelector("#draggable"));
        WebElement droppable = driver.findElement(By.cssSelector("#droppable"));
        Actions a = new Actions(driver);
        a.dragAndDrop(draggable,droppable).build().perform();
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[text()='Resizable']")).click();



    }
}

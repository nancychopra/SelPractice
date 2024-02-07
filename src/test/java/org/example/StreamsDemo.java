package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsDemo {

    @Test
    public void sortTable(){
        WebDriver driver = new ChromeDriver();
        driver.get(" https://rahulshettyacademy.com/seleniumPractise/#/offers");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("table th:nth-child(1)")).click();
        List<WebElement> elements =driver.findElements(By.xpath("//table/tbody/tr/td[1]"));

        List<String> originalList=elements.stream().map(s->s.getText()).collect(Collectors.toList());
        List<String> sortedList=elements.stream().map(s->s.getText()).sorted().collect(Collectors.toList());

        Assert.assertEquals(originalList,sortedList);
        List<String> price;
        do{

            price=elements.stream().filter(s->s.getText().equals("Wheat")).map(s->s.findElement(By.xpath("following-sibling::td[1]")).getText()).collect(Collectors.toList());

            if(price.size()<1){
                driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
                elements =driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
            }


        }while(price.size()<1);

        //Get the price for rice

        //driver.close();

        System.out.println(price.get(0));




    }


}

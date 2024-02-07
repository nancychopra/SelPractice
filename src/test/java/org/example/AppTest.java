package org.example;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    public void shouldAnswerWithTrue()
    {

       // System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver");
       // System.setProperty("webdriver.gecko.driver","/Users/nancychopra/Software Downloads/geckodriver");

        WebDriver fDriver= new ChromeDriver();
        //WebDriver fDriver= new FirefoxDriver();
        fDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        fDriver.manage().window().maximize();
        fDriver.get("https://www.google.com");
        fDriver.navigate().to("https://rahulshettyacademy.com/");
        System.out.println(fDriver.getTitle());
        fDriver.navigate().back();
        System.out.println(fDriver.getTitle());
        fDriver.navigate().forward();
        System.out.println(fDriver.getTitle());

        fDriver.close();
    }

    public void dropdown() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        WebElement element = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropDown= new Select(element);
        dropDown.selectByIndex(1);
        System.out.println(dropDown.getFirstSelectedOption().getText());
        dropDown.selectByValue("AED");
        System.out.println(dropDown.getFirstSelectedOption().getText());
        driver.findElement(By.id("divpaxinfo")).click();

        for (int i = 0; i <5 ; i++)
        {
            driver.findElement(By.id("hrefIncAdt")).click();
        }
        Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(),"6 Adult");


        //dynamic dropdown
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        //driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
        driver.findElement((By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA'] "))).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector("#autosuggest")).sendKeys("in");
        Thread.sleep(2000);

        for(WebElement options: driver.findElements(By.cssSelector("#ui-id-1 li a"))){
            System.out.println(options.getText());
            if(options.getText().equalsIgnoreCase("British Indian Ocean Territory")){
                options.click();
                System.out.println("Found it");
                break;
            }
        }

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_SeniorCitizenDiscount']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id='ctl00_mainContent_chk_SeniorCitizenDiscount']")).isSelected());
        driver.findElements(By.cssSelector("input[type='checkbox']")).size();

        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#ctl00_mainContent_view_date1")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        Thread.sleep(2000);







        driver.close();

    }
    public void calendar() throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        System.out.println(driver.findElement(By.cssSelector("#Div1")).getAttribute("style").contains("0.5"));

        driver.findElement(By.cssSelector("#ctl00_mainContent_view_date1")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        Thread.sleep(2000);


        System.out.println(driver.findElement(By.cssSelector("#Div1")).getAttribute("style").contains("1"));


        driver.quit();

    }


    public void testAlert() throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.cssSelector("#name")).sendKeys("Nancy Chopra");
        driver.findElement(By.cssSelector("#confirmbtn")).click();
        Thread.sleep(1000);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        driver.quit();

    }


    public void testCart()  throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        //driver.manage().window().maximize();

        String[] veggies={"Brocolli","Cucumber","Tomato"};
        addCart(driver,veggies);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter promo code']")));
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");

        driver.findElement(By.cssSelector("button.promoBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Code applied ..!']")));
        System.out.println(driver.findElement(By.xpath("//span[text()='Code applied ..!']")).getText());



        driver.close();
    }

    public void addCart (WebDriver driver, String[] veggies) throws InterruptedException{

        List itemsNeeded = Arrays.asList(veggies);
        Thread.sleep(1000);
        int j=0;
        //List<WebElement> list =driver.findElements(By.xpath("//div[@class='product-action']"));
        List<WebElement> products =driver.findElements(By.xpath("//h4[@class='product-name']"));
        for (int i = 0; i <products.size() ; i++) {
            String productFound=products.get(i).getText();
            if(itemsNeeded.contains(productFound.split("-")[0].trim())){
                do{

                    driver.findElements(By.xpath("//div[@class='product-action']")).get(i).click();
                    j++;

                }while(j<itemsNeeded.size());
            }
        }

        driver.findElement(By.cssSelector(".cart-icon")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();

    }
    @Test
    public void fluentWait()throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.cssSelector("#start button")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        WebElement element=wait.until(new Function<WebDriver, WebElement>() {
                                          @Override
                                          public WebElement apply(WebDriver webDriver) {
                                              {
                                                  if (driver.findElement(By.xpath(" //h4[text()='Hello World!']")).isDisplayed())
                                                      return driver.findElement(By.xpath(" //h4[text()='Hello World!']"));
                                                  else return null;
                                              }
                                          }
                                      }
            );

//        WebElement element = wait.until((e)-> {
//            if (e.findElement(By.xpath(" //h4[text()='Hello World!']")).isDisplayed())
//                return e.findElement(By.xpath(" //h4[text()='Hello World!']"));
//            else { return null;}
//
//        });
        System.out.println(element.getText());
        //h4[text()='Hello World!']

        driver.close();

        Thread.sleep(1000);

    }

    @Test(enabled = true)
    public void newFeature() throws IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();
        WebElement element=driver.findElement(By.cssSelector("input.form-control[name='name'][type='text']"));
        System.out.println(driver.findElement(RelativeLocator.with(By.tagName("label")).above(element)).getText());
        File file =driver.findElement(RelativeLocator.with(By.tagName("label")).above(element)).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("/Users/nancychopra/workspace/Selenium/newFeature/screenshot.png" ));
        System.out.println(driver.findElement(RelativeLocator.with(By.tagName("label")).above(element)).getRect().getDimension().getHeight());
        driver.close();



    }
}

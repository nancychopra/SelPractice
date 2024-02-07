package designpatterns.factory;

import e2e.testcomponents.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Properties;

public class FactoryTest {

    private WebDriver driver;
    private GooglePage googlePage;

    @BeforeClass
    public void setup() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//Global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String browserName = System.getProperty("browser") == null ? prop.getProperty("browser") : System.getProperty("browser");
        switch(browserName)

        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");

                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chromeheadless":
                ChromeOptions options = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", "/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
                options.addArguments("--headless=new");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
                break;
        }

    }


    @Test(dataProvider="data")
    public void test(String language,String keyword) {



         this.googlePage= GoogleFactory.get(language,driver);
         googlePage.launch();
         googlePage.search(keyword);
         int count =googlePage.results();
         System.out.println("Result Count : " + count);




    }

    @DataProvider(name="data")
    public Object[][] getData(){
        Object[][] data = {
                {"ENG","selenium"},
                {"ARB","docker"},
                {"FR","design patterns"}

        };
        return data;
    }


    @AfterTest
    public void finalMethod(){
        System.out.println("After Test");

        driver.quit();
    }


}

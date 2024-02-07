package e2e.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;

    public WebDriver initializeDriver() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//resources//Global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String browserName = System.getProperty("browser")==null?prop.getProperty("browser"):System.getProperty("browser");
        switch (browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
                
                driver= new ChromeDriver(); break;
            case "firefox": driver= new FirefoxDriver(); break;
            case "chromeheadless":
                ChromeOptions options = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
                options.addArguments("--headless=new");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440,900));
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;

    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException, InterruptedException {
        driver =initializeDriver();
        driver.get("https://rahulshettyacademy.com/client/");
        loginPage = new LoginPage(driver);
        System.out.println("Running the before method");
        return loginPage;

    }

    @AfterMethod(alwaysRun = true)
    public void close(){
        driver.close();
    }

    public List<HashMap<String, String>> getObjectFromJson(String filepath) throws IOException {
        String jsonString= FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        ObjectMapper mapper= new ObjectMapper();
        List<HashMap<String,String>> listMap =mapper.readValue(jsonString, new TypeReference<List<HashMap<String, String>>>() {});
        return listMap;

    }

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot screenshot= (TakesScreenshot)driver;
        File src=screenshot.getScreenshotAs(OutputType.FILE);
        String pathName=System.getProperty("user.dir") + File.separator + "report" +  File.separator + testCaseName + ".png";
        File dest = new File(System.getProperty("user.dir") +  File.separator + "report" + File.separator + testCaseName + ".png");
        FileUtils.copyFile(src,dest);
        System.out.println(dest.getAbsolutePath());
        return dest.getAbsolutePath();

    }

    public ExtentReports getExtentTest(){
        String path = System.getProperty("user.dir") + File.separator + "report" + File.separator + "index.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        ExtentReports report = new ExtentReports();

        report.attachReporter(spark);

        spark.config().setReportName("Sample Report");
        spark.config().setDocumentTitle("Selenium Tests");
        spark.config().setReportName("nancy");

        return report;
    }
}

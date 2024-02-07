package e2e.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import e2e.testcomponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener {

    ExtentReports extent = getExtentTest();
    ExtentTest test;
    ThreadLocal<ExtentTest> local = new ThreadLocal<>();

    public  void onTestStart(ITestResult result) {
        test =extent.createTest(result.getMethod().getMethodName());
        local.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        local.get().log(Status.PASS,"Test has passed");


    }

    public void onTestFailure(ITestResult result) {
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = null;
        try {
            path = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        local.get().addScreenCaptureFromPath(result.getMethod().getMethodName()+".png");
        local.get().fail(result.getThrowable());

    }

    public  void onFinish(ITestContext context) {
        extent.flush();
    }

}

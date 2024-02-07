package NewFeatures;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.fetch.Fetch;
import org.openqa.selenium.devtools.v115.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v115.network.Network;
import org.openqa.selenium.devtools.v115.network.model.ConnectionType;
import org.openqa.selenium.devtools.v115.network.model.ErrorReason;
import org.openqa.selenium.devtools.v115.network.model.Request;
import org.openqa.selenium.devtools.v115.network.model.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkDemo {
    ChromeDriver driver;
    DevTools tools;

    @BeforeTest
    public void setup(){
        System.out.println("Running before Test");
        System.setProperty("webdriver.chrome.driver","/Users/nancychopra/Software Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        tools = driver.getDevTools();
        tools.createSession();


    }

    @Test
    public void enableNetwork(){

        tools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        tools.addListener(Network.requestWillBeSent(),request->
                {
                    Request req= request.getRequest();
                    System.out.println(req.getUrl());
                    System.out.println(req.getPostData());

                }
                );
        tools.addListener(Network.responseReceived(),response->{
            Response res= response.getResponse();
            System.out.println(res.getStatus());

        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");


        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();



    }

    @Test
    public void mockNetwork() throws InterruptedException {
        tools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        tools.addListener(Fetch.requestPaused(),requestPaused -> {
            Request req = requestPaused.getRequest();
            if(req.getUrl().contains("shetty")){
                String mockUrl=req.getUrl().replace("=shetty","=BadGuy");
                System.out.println(mockUrl);
                tools.send(Fetch.continueRequest(requestPaused.getRequestId(),Optional.of(mockUrl),Optional.of(requestPaused.getRequest().getMethod()),Optional.empty(),Optional.empty(),
                        Optional.empty()));

            }else{

                tools.send(Fetch.continueRequest(requestPaused.getRequestId(),Optional.of(req.getUrl()),Optional.of(requestPaused.getRequest().getMethod()),Optional.empty(),Optional.empty(),
                        Optional.empty()));

            }


        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(3000);

        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        tools.send(Fetch.disable());

    }

    @Test
    public void failNetwork()  {

        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"),Optional.empty(),Optional.empty())));

        tools.send(Fetch.enable(patterns, Optional.empty()));
        tools.addListener(Fetch.requestPaused(),request->{
            tools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.ABORTED));

        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        tools.send(Fetch.disable());

    }

    @Test
    public void blockURLS(){

        tools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        tools.send(Network.setBlockedURLs(ImmutableList.of("*.jpeg","*.css")));

        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }

    @Test
    public void emulateSpeed(){
        tools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        tools.send(Network.emulateNetworkConditions(true,3000,20000,100000,Optional.of(ConnectionType.CELLULAR2G)));
        tools.addListener(Network.loadingFailed(),handler->{
            System.out.println(handler.getErrorText());
            System.out.println(handler.getTimestamp().toJson());
        });
        long startTime = System.currentTimeMillis();
//        driver.get("http://google.com");
//        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
//        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
//        String title =driver.findElement(By.cssSelector(".our-story-card-title")).getText();
//        System.out.println(title);
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }


}

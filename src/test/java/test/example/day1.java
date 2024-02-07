package test.example;

import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.annotation.Retention;
import java.util.function.DoubleToIntFunction;

public class day1 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("I am before Suite");

    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("I am before test -Day 1");

    }

    @Parameters({"URL","method"})
    @Test(enabled = true)
    public void test1(String url,String method){
        System.out.println("I am test 1");
        System.out.println("I am using paramter " + url + " " + method);
    }

    @Test(dependsOnMethods = {"test4"},dataProvider = "getData")
    public void test2(String username,String password){
        System.out.println("I am test 2");
        System.out.print(username + " ");
        System.out.println(password);
    }


    @Test(groups = {"regression"})
    public void test3(){
        System.out.println("I am test 3");
    }

    @Test(groups = {"smoke"})
    public void test4(){
        System.out.println("I am test 4");
    }


    @Test
    public void test5(){
        System.out.println("I am test 5");
        Assert.assertTrue(false);
    }

    @AfterTest
    public void afterTest(){

        System.out.println("I am after test- Day1");

    }


   @AfterSuite
    public void afterSuite(){
        System.out.println("I am after Suite");

    }

    @DataProvider
    public Object[][] getData(){

        Object[][] data = new Object[3][2];
        data[0][0]= "username0";
        data[0][1]= "passowrd0";

        data[1][0]= "username1";
        data[1][1]= "passowrd1";

        data[2][0]= "username2";
        data[2][1]= "passowrd2";
        return data;

    }

}

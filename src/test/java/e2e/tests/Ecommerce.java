package e2e.tests;

import e2e.testcomponents.BaseTest;
import e2e.testcomponents.Retry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ecommerce  extends BaseTest {

//    @Test(dataProvider = "getDataObject")
//    public void submitOrder(String username, String password, String product) throws InterruptedException {
//
//
//        //Select product from list of products and add to cart= By.cssSelection
//        loginPage.login(username,password);
//        ProductCataloguePage catalogPage = new ProductCataloguePage(driver);
//        String message=catalogPage.chooseProductAddToCart(product);
//        Assert.assertEquals(message,"Product Added To Cart");
//
//        //Go to cart
//        catalogPage.goToCart();
//        CartPage cartPage= new CartPage(driver);
//        cartPage.getProductDisplayed(product);
//        Assert.assertTrue(cartPage.getProductDisplayed(product));
//        cartPage.goToCheckout();
//
//        //Checkout
//        CheckoutPage checkout = new CheckoutPage(driver);
//        checkout.selectCountry("india");
//        checkout.submitOrder();
//
//        OrderPage orderPage = new OrderPage(driver);
//        Assert.assertEquals(orderPage.getConfirmation(),"THANKYOU FOR THE ORDER.");
//
//
//    }

    @Test(dataProvider = "getDataJsonMap")
    public void submitOrder(HashMap<String,String> map) throws InterruptedException, IOException {


        //Select product from list of products and add to cart= By.cssSelection
        loginPage.login(map.get("user"),map.get("pass"));
        ProductCataloguePage catalogPage = new ProductCataloguePage(driver);
        String message=catalogPage.chooseProductAddToCart(map.get("product"));
        Assert.assertEquals(message,"Product Added To Cart");
        //getScreenshot("submitOrder");

        //Go to cart
        catalogPage.goToCart();
        CartPage cartPage= new CartPage(driver);
        cartPage.getProductDisplayed(map.get("product"));
        Assert.assertTrue(cartPage.getProductDisplayed(map.get("product")));
        cartPage.goToCheckout();

        //Checkout
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.selectCountry("india");
        checkout.submitOrder();

        OrderPage orderPage = new OrderPage(driver);
        Assert.assertEquals(orderPage.getConfirmation(),"THANKYOU FOR THE ORDER.");


    }



    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistory(){
        System.out.println("hahaha");

    }


    @Test(groups={"smoke"},retryAnalyzer = Retry.class)
    public void orderHistory1(){
        Assert.assertEquals("Incorrect password","fff");

    }

    @DataProvider
    public Object[][] getDataObject(){
        Object[][] data = {{"nancy.chopra@gmail.com","8YmuKuG4wMNKB.","ADIDAS ORIGINAL"},{"nancy.chopra@gmail.com","8YmuKuG4wMNKB.","ZARA COAT 3"}};
        return data;
    }

    @DataProvider
    public Object[][] getDataHashMap(){

        Map<String,String> map = new HashMap<String,String>();
        map.put("user","nancy.chopra@gmail.com");
        map.put("pass","8YmuKuG4wMNKB.");
        map.put("product","ADIDAS ORIGINAL");

        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("user","nancy.chopra@gmail.com");
        map1.put("pass","8YmuKuG4wMNKB.");
        map1.put("product","ZARA COAT 3");

        return new Object[][]{{map},{map1}};
    }

    @DataProvider
    public Object[][] getDataJsonMap() throws IOException {

        List<HashMap<String,String>> map =getObjectFromJson(System.getProperty("user.dir") + "//src//test//java//data//products.json");

        return new Object[][]{{map.get(0)},{map.get(1)}};
    }

}

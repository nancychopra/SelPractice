package stepdefinition;

import e2e.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.*;

import java.io.IOException;

public class StepDefinitionImplementation extends BaseTest {

  public LoginPage loginPage;
  public ProductCataloguePage catalogPage;
  public String confirmationMessage;

  @Given("I landed on Ecommerce page")
  public void I_landed_on_Ecommerce_page() throws IOException, InterruptedException {
    loginPage= launchApplication();

  }
  @Given("Logged in with username {string} and password {string}")
  public void Logged_in_with_username_and_password(String username, String password) throws InterruptedException {
    loginPage.login(username,password);
    catalogPage = new ProductCataloguePage(driver);

  }

  @When("I add product {string} to cart")
  public void I_add_product_to_cart(String product){
    String message=catalogPage.chooseProductAddToCart(product);
    Assert.assertEquals(message,"Product Added To Cart");

  }

  @And("Checkout {string} and submit order")
  public void Checkout_and_submit_order(String product){

    catalogPage.goToCart();
    CartPage cartPage= new CartPage(driver);
    cartPage.getProductDisplayed(product);
    Assert.assertTrue(cartPage.getProductDisplayed(product));
    cartPage.goToCheckout();

    //Checkout
    CheckoutPage checkout = new CheckoutPage(driver);
    checkout.selectCountry("india");
    checkout.submitOrder();

    OrderPage orderPage = new OrderPage(driver);
    confirmationMessage=orderPage.getConfirmation();

  }

  @Then("{string} message")
  public void message(String string){

    Assert.assertEquals(confirmationMessage,string);
    driver.close();


  }

  @Then("{string} login message displayed")
  public void login_message_displayed(String message){
    Assert.assertEquals(loginPage.getError(),message);
    driver.close();
  }






}

package e2e.tests;

import e2e.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {

    @Test
    public void loginValidation() throws InterruptedException {
        loginPage.login("nancy.chopra@gmail2.com","8YmuKuG4wMNKB.");
        Assert.assertEquals(loginPage.getError(),"Incorrect email or password.");

    }

    @Test
    public void loginValidation1() throws InterruptedException {
        loginPage.login("nancy.chopra@gmail1.com","8YmuKuG4wMNKB.");
        Assert.assertEquals(loginPage.getError(),"Incorrect email or password.");

    }
}

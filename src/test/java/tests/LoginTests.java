package tests;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

@Listeners(TestListener.class)
public class LoginTests extends BaseTest {

    @Test
    public void loginWithValidCredentials() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("validEmail"),
                ConfigReader.getProperty("validPassword")
        );

        Assert.assertTrue(
                driver.findElement(
                        By.xpath("//li/a[contains(.,'Logged in as')]")
                ).isDisplayed(),
                "User should be logged in and name visible in header"
        );

    }

    @Test
    public void loginWithInvalidCredentials() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                "wrong@email.com",
                "wrongPassword"
        );

        Assert.assertEquals(
                loginPage.getLoginErrorMessage(),
                "Your email or password is incorrect!",
                "Error message should appear for invalid credentials"
        );
    }
}
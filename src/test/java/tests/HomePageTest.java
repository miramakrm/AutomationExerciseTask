package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void openSignupPage() {

        HomePage homePage = new HomePage(driver);

        homePage.clickSignupLogin();
    }
}
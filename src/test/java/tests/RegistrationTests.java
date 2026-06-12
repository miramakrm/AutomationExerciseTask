package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;

@Listeners(TestListener.class)
public class RegistrationTests extends BaseTest {

    @Test
    public void registerNewUser() {

        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        SignupPage signupPage = new SignupPage(driver);

        String email = "automation_"
                + System.currentTimeMillis()
                + "@test.com";

        signupPage.signup("john", email);

        Assert.assertTrue(
                signupPage.isEnterAccountDisplayed(),
                "Enter Account Information page should be displayed"
        );

        signupPage.fillAccountInformation(
                "123123",
                "10", "5", "2000",
                "John", "Doe",
                "123 Test Street", "California",
                "los angeless", "90001", "01012345678"
        );

        signupPage.clickCreateAccount();

        Assert.assertTrue(
                signupPage.isAccountCreated(),
                "Account Created! message should be displayed"
        );
    }

    @Test
    public void registerWithEmptyFields() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        SignupPage signupPage = new SignupPage(driver);

        // اضغط signup من غير ما تملا الـ email
        signupPage.signupWithEmptyEmail("john");

        Assert.assertTrue(
                signupPage.isEmailValidationErrorShown(),
                "Validation error should appear for empty email"
        );
    }
    }

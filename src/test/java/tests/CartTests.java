package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

@Listeners(TestListener.class)
public class CartTests extends BaseTest {

    private void loginFirst() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("validEmail"),
                ConfigReader.getProperty("validPassword")
        );
    }

    @Test
    public void addProductToCart() {

        loginFirst();

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToProducts();
        cartPage.addFirstProductToCart();
        cartPage.goToCart();

        String productName = cartPage.getCartProductName();

        Assert.assertFalse(
                productName.isEmpty(),
                "Product name in cart should not be empty"
        );
    }

    @Test
    public void removeProductFromCart() {

        loginFirst();

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToProducts();
        cartPage.addFirstProductToCart();
        cartPage.goToCart();

        cartPage.removeProductFromCart();

        Assert.assertTrue(
                cartPage.isCartEmpty(),
                "Cart should show empty message after removal"
        );
    }
}
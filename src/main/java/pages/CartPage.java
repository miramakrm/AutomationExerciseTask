package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class CartPage extends BasePage {

    private final Actions actions;

    private final By productOverlay =
            By.cssSelector(".productinfo.text-center");

    private final By addToCartBtn =
            By.cssSelector(".productinfo.text-center a.btn");

    private final By continueShoppingBtn =
            By.cssSelector("button[data-dismiss='modal']");

    private final By cartLink =
            By.cssSelector("a[href='/view_cart']");

    private final By cartProductName =
            By.cssSelector(".cart_description h4 a");

    private final By removeItemBtn =
            By.cssSelector("a.cart_quantity_delete");

    private final By emptyCartMessage =
            By.xpath("//*[contains(text(),'Cart is empty!')]");

    public CartPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    public void navigateToProducts() {
        driver.get("https://automationexercise.com/products");
    }

    public void addFirstProductToCart() {
        closeAdsIfPresent();

        WebElement overlay = waitUtils.waitForVisibility(productOverlay);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", overlay);

        try { Thread.sleep(1000); } catch (Exception ignored) {}

        closeAdsIfPresent();

        // JS hover ثم JS click
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles: true}));", overlay
        );

        try { Thread.sleep(500); } catch (Exception ignored) {}

        WebElement btn = waitUtils.waitForVisibility(addToCartBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        WebElement continueBtn = waitUtils.waitForVisibility(continueShoppingBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
    }
    public void goToCart() {
        safeClick(cartLink);
    }

    public String getCartProductName() {
        return safeGetText(cartProductName);
    }

    public void removeProductFromCart() {
        WebElement btn = waitUtils.waitForClickability(removeItemBtn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        waitUtils.waitForInvisibility(removeItemBtn);
        waitUtils.waitForVisibility(emptyCartMessage);
    }

    public boolean isCartEmpty() {
        try {
            return waitUtils.waitForVisibility(emptyCartMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
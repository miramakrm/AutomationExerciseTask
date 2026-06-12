package pages;

import org.openqa.selenium.*;
import utils.WaitUtils;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    protected void closeAdsIfPresent() {
        try {
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

            for (WebElement iframe : iframes) {
                try {
                    driver.switchTo().frame(iframe);

                    List<WebElement> closeBtns = driver.findElements(
                            By.cssSelector(".fc-button, .fc-close, .fc-dismiss")
                    );

                    if (!closeBtns.isEmpty()) {
                        closeBtns.get(0).click();
                        return;
                    }

                } catch (Exception ignored) {
                } finally {
                    driver.switchTo().defaultContent();
                }
            }
        } catch (Exception ignored) {}
    }

    protected void click(By locator) {
        closeAdsIfPresent();
        WebElement element = waitUtils.waitForClickability(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void safeClick(By locator) {
        click(locator);
    }

    protected void type(By locator, String text) {
        closeAdsIfPresent();
        waitUtils.waitForVisibility(locator).clear();
        waitUtils.waitForVisibility(locator).sendKeys(text);
    }

    protected void safeType(By locator, String text) {
        type(locator, text);
    }

    protected String getText(By locator) {
        closeAdsIfPresent();
        return waitUtils.waitForVisibility(locator).getText();
    }

    protected String safeGetText(By locator) {
        return getText(locator);
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    private By signupName   = By.cssSelector("[data-qa='signup-name']");
    private By signupEmail  = By.cssSelector("[data-qa='signup-email']");
    private By signupButton = By.cssSelector("[data-qa='signup-button']");

    private By enterAccountTitle = By.xpath("//b[contains(text(),'Enter Account Information')]");
    private By titleMrs = By.id("id_gender2");

    private By passwordField = By.cssSelector("[data-qa='password']");
    private By dobDay = By.cssSelector("[data-qa='days']");
    private By dobMonth = By.cssSelector("[data-qa='months']");
    private By dobYear = By.cssSelector("[data-qa='years']");

    private By firstName = By.cssSelector("[data-qa='first_name']");
    private By lastName = By.cssSelector("[data-qa='last_name']");
    private By address = By.cssSelector("[data-qa='address']");
    private By country = By.cssSelector("[data-qa='country']");
    private By state = By.cssSelector("[data-qa='state']");
    private By city = By.cssSelector("[data-qa='city']");
    private By zipcode = By.cssSelector("[data-qa='zipcode']");
    private By mobileNumber = By.cssSelector("[data-qa='mobile_number']");

    private By createAccountBtn = By.cssSelector("[data-qa='create-account']");
    private By accountCreatedTitle = By.xpath("//b[contains(text(),'Account Created!')]");
    private By continueBtn = By.cssSelector("[data-qa='continue-button']");

    public void signup(String name, String email) {
        waitUtils.waitForVisibility(signupName).sendKeys(name);
        waitUtils.waitForVisibility(signupEmail).sendKeys(email);
        waitUtils.waitForClickability(signupButton).click();
    }

    public boolean isEnterAccountDisplayed() {
        return waitUtils.waitForVisibility(enterAccountTitle).isDisplayed();
    }

    public void fillAccountInformation(String password,
                                       String day, String month, String year,
                                       String fName, String lName,
                                       String addr, String stateVal,
                                       String cityVal, String zip,
                                       String mobile) {

        waitUtils.waitForClickability(titleMrs).click();
        waitUtils.waitForVisibility(passwordField).sendKeys(password);

        new Select(driver.findElement(dobDay)).selectByValue(day);
        new Select(driver.findElement(dobMonth)).selectByValue(month);
        new Select(driver.findElement(dobYear)).selectByValue(year);

        waitUtils.waitForVisibility(firstName).sendKeys(fName);
        waitUtils.waitForVisibility(lastName).sendKeys(lName);
        waitUtils.waitForVisibility(address).sendKeys(addr);

        new Select(driver.findElement(country)).selectByVisibleText("United States");

        waitUtils.waitForVisibility(state).sendKeys(stateVal);
        waitUtils.waitForVisibility(city).sendKeys(cityVal);
        waitUtils.waitForVisibility(zipcode).sendKeys(zip);
        waitUtils.waitForVisibility(mobileNumber).sendKeys(mobile);
    }

    public void clickCreateAccount() {
        waitUtils.waitForClickability(createAccountBtn).click();
    }

    public boolean isAccountCreated() {
        return waitUtils.waitForVisibility(accountCreatedTitle).isDisplayed();
    }

    public void clickContinueAfterRegistration() {
        waitUtils.waitForClickability(continueBtn).click();
    }
    public void signupWithEmptyEmail(String name) {
        waitUtils.waitForVisibility(signupName).sendKeys(name);

        waitUtils.waitForClickability(signupButton).click();
    }

    public boolean isEmailValidationErrorShown() {

        return driver.getCurrentUrl().contains("login");
    }
}
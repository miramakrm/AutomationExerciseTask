# AutomationExercise — Selenium + TestNG Framework

A test automation suite built with **Java + Selenium 4 + TestNG** targeting [automationexercise.com](https://automationexercise.com), following the Page Object Model design pattern.

---

## Prerequisites

| Tool | Version |
|------|---------|
| Java JDK | 11 or higher |
| Maven | 3.6+ |
| Google Chrome | Latest stable |
| Git | Any recent version |

> **Note:** ChromeDriver is managed automatically via WebDriverManager — no manual driver setup needed.

---

## Project Structure

```
AutomationExerciseTask/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── BasePage.java          # Shared actions (click, type, getText) + ads handler
│   │       │   ├── HomePage.java          # Home page locators and actions
│   │       │   ├── LoginPage.java         # Login form interactions
│   │       │   ├── SignupPage.java        # Registration form interactions
│   │       │   └── CartPage.java          # Product browsing and cart actions
│   │       └── utils/
│   │           ├── ConfigReader.java      # Reads values from config.properties
│   │           ├── DriverFactory.java     # WebDriver init and teardown (ThreadLocal)
│   │           ├── ScreenshotUtils.java   # Captures screenshots on failure
│   │           └── WaitUtils.java         # Explicit wait helpers (WebDriverWait)
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java          # @BeforeMethod / @AfterMethod setup
│           ├── listeners/
│           │   └── TestListener.java      # TestNG listener — screenshot on failure
│           └── tests/
│               ├── RegistrationTests.java # TC-REG-01, TC-REG-02
│               ├── LoginTests.java        # TC-LOG-01, TC-LOG-02
│               ├── CartTests.java         # TC-CART-01, TC-CART-03
│               └── HomePageTest.java      # Smoke check
├── src/main/resources/
│   └── config.properties                  # Externalized test data (URL, credentials)
├── testng.xml                             # Suite file — runs tests in order
└── pom.xml                                # Maven dependencies
```

---

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/AutomationExerciseTask.git
cd AutomationExerciseTask
```

### 2. Configure test data

Open `src/main/resources/config.properties` and verify/update:

```properties
baseUrl=https://automationexercise.com
validEmail=your_registered_email@test.com
validPassword=your_password
```

### 3. Run the full suite via Maven

```bash
mvn clean test
```

### 4. Run via testng.xml directly (IntelliJ)

Right-click `testng.xml` → **Run**

---

## Test Cases

| ID | Module | Scenario | Type |
|----|--------|----------|------|
| TC-REG-01 | Registration | Successful registration with valid data | Positive |
| TC-REG-02 | Registration | Registration fails with empty email field | Negative |
| TC-LOG-01 | Login | Successful login with valid credentials | Positive |
| TC-LOG-02 | Login | Login fails with invalid credentials | Negative |
| TC-CART-01 | Cart | Add a product to the cart | Positive |
| TC-CART-03 | Cart | Remove a product from the cart | Negative |

---

## Key Design Decisions

- **Page Object Model** — each page has its own class; locators and actions are fully encapsulated.
- **Explicit Waits Only** — `WaitUtils` wraps `WebDriverWait` with `ExpectedConditions`. No `Thread.sleep()` is used.
- **Ads Handling** — `automationexercise.com` serves aggressive Google Ads iframes. `BasePage.closeAdsIfPresent()` dismisses them before every interaction; all clickable elements also fall back to JavaScript click to bypass iframe interception.
- **Externalized Config** — all URLs and credentials live in `config.properties`, read via `ConfigReader`. Nothing is hardcoded in test methods.
- **Screenshot on Failure** — `TestListener` implements `ITestListener.onTestFailure()` and saves a timestamped PNG via `ScreenshotUtils`.
- **ThreadLocal WebDriver** — `DriverFactory` uses `ThreadLocal<WebDriver>` for safe parallel execution readiness.

---

## Screenshots

Failed-test screenshots are saved automatically to:

```
screenshots/<TestName>_<timestamp>.png
```

---

## Dependencies (pom.xml highlights)

```xml
<dependencies>
    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.34.0</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.11.0</version>
    </dependency>

    <!-- WebDriverManager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>6.3.2</version>
    </dependency>
</dependencies>
```

---

## Author

Built as part of a Software Test Engineer technical assessment.

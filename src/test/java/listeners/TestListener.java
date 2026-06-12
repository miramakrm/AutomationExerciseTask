package listeners;

import base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();

        ScreenshotUtils.takeScreenshot(
                DriverFactory.getDriver(),
                testName
        );

        System.out.println("Screenshot captured for: " + testName);
    }
}
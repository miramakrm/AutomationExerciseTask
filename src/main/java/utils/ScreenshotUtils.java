package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static void takeScreenshot(
            WebDriver driver,
            String testName) {

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        File destination =
                new File("screenshots/"
                        + testName
                        + ".png");

        try {

            FileUtils.copyFile(
                    source,
                    destination
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
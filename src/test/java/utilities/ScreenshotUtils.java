package utilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/screenshots/";

    public static byte[] takeScreenshotAsByte(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Creates directory if it doesn't exist
            new File(SCREENSHOT_DIR).mkdirs();

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;

            // Takes and saves screenshots
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));

            return filePath;
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
}
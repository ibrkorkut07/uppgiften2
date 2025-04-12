package utilities;

// Import required classes
import org.apache.commons.io.FileUtils;  // File operations utility
import org.openqa.selenium.OutputType;  // Selenium screenshot output types
import org.openqa.selenium.TakesScreenshot;  // Interface for screenshots
import org.openqa.selenium.WebDriver;  // WebDriver interface

import java.io.File;  // File system operations
import java.io.IOException;  // I/O exception handling
import java.text.SimpleDateFormat;  // Date formatting
import java.util.Date;  // Date operations

public class ScreenshotUtilsEXP {
    // Directory to store screenshots (in project's test-output folder)
    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/test-output/screenshots/";

    // Takes screenshot and returns as byte array (for Cucumber reports)
    public static byte[] takeScreenshotAsByte(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Takes screenshot and saves to file system
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            new File(SCREENSHOT_DIR).mkdirs();

            // Generate timestamp for unique filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Create filename with test name and timestamp
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;

            // Capture screenshot as temporary file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to target location
            FileUtils.copyFile(screenshot, new File(filePath));

            return filePath;  // Returns path to saved screenshot
        } catch (IOException e) {
            // Logs error if screenshot fails
            System.err.println("Failed to take screenshot: " + e.getMessage());
            return null;
        }
    }
}
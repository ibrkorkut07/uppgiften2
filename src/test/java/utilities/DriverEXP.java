package utilities;  // Utility classes package

// Import Selenium WebDriver and browser-specific drivers
import org.openqa.selenium.WebDriver;  // Main Selenium interface
import org.openqa.selenium.chrome.ChromeDriver;  // Chrome browser driver
import org.openqa.selenium.edge.EdgeDriver;  // Edge browser driver
import org.openqa.selenium.firefox.FirefoxDriver;  // Firefox browser driver
import org.openqa.selenium.firefox.FirefoxOptions;  // Firefox configuration

public class DriverEXP {
    // Singleton WebDriver instance
    private static WebDriver driver;

    // Gets or creates WebDriver instance
    public static WebDriver getDriver() {
        // Gets browser type from system property, defaults to "chrome"
        String browser = System.getProperty("browser", "chrome");

        // Creates new driver instance if one doesn't exist
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();  // Chrome instance
                    break;
                case "firefox":
                    FirefoxOptions options = new FirefoxOptions();
                    // Sets custom Firefox binary location
                    options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
                    driver = new FirefoxDriver(options);  // Firefox with options
                    break;
                case "edge":
                    driver = new EdgeDriver();  // Edge instance
                    break;
                default:
                    // Throws exception for unsupported browsers
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    // Cleans up WebDriver instance
    public static void quitDriver() {
        if (driver != null) {
            // Note: Actual quit is commented out, only nullifies reference
            // driver.quit();
            driver = null;  // Allows garbage collection
        }
    }
}

/*
Key Points:

Implements Singleton pattern for WebDriver

Supports multiple browsers (Chrome, Firefox, Edge)

Configurable via system property (-Dbrowser=firefox)

Custom Firefox binary path configuration

Basic driver lifecycle management

Currently doesn't actually quit the driver (commented out)
 */
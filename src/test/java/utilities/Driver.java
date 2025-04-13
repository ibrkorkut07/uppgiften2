package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    FirefoxOptions options = new FirefoxOptions();
                    options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
                    driver = new FirefoxDriver(options);
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            // driver.quit();
            driver = null;
        }
    }
}
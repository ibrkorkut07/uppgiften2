package stepdefinitions;  // Contains all step definitions

// Import required testing and Cucumber classes
import io.cucumber.java.*;  // All Cucumber annotations
import io.cucumber.java.en.*;  // Step definition annotations
import org.apache.commons.io.FileUtils;
import org.junit.Assert;  // JUnit assertions
import org.openqa.selenium.*;  // Selenium core classes
import org.openqa.selenium.support.ui.ExpectedConditions;  // Wait conditions
import org.openqa.selenium.support.ui.WebDriverWait;  // Explicit wait
import pages.RegistrationPage;  // Our page object
import utilities.ConfigReader;  // Reads config properties
import utilities.Driver;  // Manages WebDriver
import java.io.File;  // File handling
import java.time.Duration;  // Time unit for waits

public class RegistrationStepsEXP {
    // WebDriver instance for browser interaction
    private WebDriver driver;

    // Page Object containing elements and methods
    private RegistrationPage registrationPage;

    // Explicit wait for synchronization
    private WebDriverWait wait;

    // Helper method to take screenshots
    private void takeScreenshot(String name) {
        try {
            // Creates screenshot file from driver
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            // Copies screenshot to target location
            FileUtils.copyFile(screenshot,
                    new File("test-output/screenshots/" + name + ".png"));
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    // Runs before each scenario
    @Before
    public void setup() {
        // Gets shared driver instance
        this.driver = Driver.getDriver();

        // Maximizes browser window
        driver.manage().window().maximize();

        // Initializes explicit wait with 10 second timeout
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initializes page object
        this.registrationPage = new RegistrationPage(driver);
    }

    // Runs after each scenario
    @After
    public void afterScenario(Scenario scenario) {
        // Takes screenshot with scenario name and status
        takeScreenshot(scenario.getName() +
                (scenario.isFailed() ? "_FAILED" : "_PASSED"));

        // Quits browser
        Driver.quitDriver();
    }

    // Step definition for clicking create account button
    @When("Click on Create A New Account button")
    public void clickOnCreateANewAccountButton() {
        registrationPage.createANewAccount.click();
    }

    // Step definition for entering all registration details
    @When("Enter valid {string}, {string}, {string}, {string}, {string}, {string}, {string} registration details:")
    public void enterFullRegistrationDetails(String dob, String firstName, String lastName,
                                             String email, String confirmEmail,
                                             String password, String confirmPassword) {
        // Enters each field value with implicit waits
        registrationPage.dateOfBirthField.sendKeys(dob);
        registrationPage.firstNameField.sendKeys(firstName);
        registrationPage.lastNameField.sendKeys(lastName);
        registrationPage.emailField.sendKeys(email);
        registrationPage.confirmEmailField.sendKeys(confirmEmail);
        registrationPage.passwordField.sendKeys(password);
        registrationPage.confirmPasswordField.sendKeys(confirmPassword);
    }

    // Verifies thank you message appears
    @Then("See THANK YOU FOR CREATING AN ACCOUNT Text")
    public void verifyThankYouMessage() {
        // Uses explicit wait to verify element visibility
        Assert.assertTrue("Thank you message not displayed",
                wait.until(ExpectedConditions.visibilityOf(
                        registrationPage.thankYouHeader)).isDisplayed());
    }

    // Other step definitions follow similar patterns...
}
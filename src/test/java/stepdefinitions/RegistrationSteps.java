package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import utilities.ConfigReader;
import utilities.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.*;
import java.io.File;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class RegistrationSteps {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private WebDriverWait wait;
    // Add screenshot method here ▼
    private void takeScreenshot(String name) {
        try {
            // 1. Create screenshots folder if it doesn't exist
            File folder = new File("screenshots");
            if (!folder.exists()) {
                folder.mkdir();
            }

            // 2. Take screenshot and save
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(folder, name + ".png"));

            // 3. Print confirmation
            System.out.println("Screenshot saved to: " + folder.getAbsolutePath() + "/" + name + ".png");
        } catch (Exception e) {
            System.out.println("Couldn't save screenshot: " + e.getMessage());
        }
    }

    @Before
    public void setup() {
        this.driver = Driver.getDriver(); // Gets browser from system property
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.registrationPage = new RegistrationPage(driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        takeScreenshot(scenario.isFailed() ? "FAILED" : "PASSED");
        Driver.quitDriver();
    }

    @When("Click on Create A New Account button")
    public void clickOnCreateANewAccountButton() {
        registrationPage.createANewAccount.click();
    }

    @When("Click on Create An Account button")
    public void clickOnCreateAnAccountButton() {
        registrationPage.createAccountButton.click();
    }

    @When("Accept Terms and Conditions")
    public void acceptTerms() {
        registrationPage.termsAcceptionBox.click();
    }

    @And("Confirm aged over Eighteen")
    public void confirmAgedOver() {
        registrationPage.age18ConfirmLabel.click();
    }

@When("Accept Code of Ethics and Conduct")
public void acceptCodeOfEthicsAndConduct() {
    // 1. Scroll to bottom of page (brute force)
    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    // 2. Short wait for scroll to complete
    try { Thread.sleep(1000); } catch (InterruptedException e) {}

    // 3. Direct JavaScript click (3 attempts)
    for (int i = 0; i < 3; i++) {
        ((JavascriptExecutor)driver).executeScript(
                "var elem = document.querySelector('input[id*=\"codeofethics\"], label[for*=\"codeofethics\"]');" +
                        "if (elem) elem.click();"
        );
        try { Thread.sleep(500); } catch (InterruptedException e) {}
    }
}

    @And("Click on Confirm and Join button")
    public void clickOnConfirmAndJoinButton() {
        // Scroll to button
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                registrationPage.confirmAndJoinButton
        );

        // Click with JavaScript as fallback
        try {
            registrationPage.confirmAndJoinButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor)driver).executeScript(
                    "arguments[0].click();",
                    registrationPage.confirmAndJoinButton
            );
        }
    }

    @Then("See THANK YOU FOR CREATING AN ACCOUNT Text")
    public void seeThankYouText() {
        try {
            // Wait for thank you message to appear
            WebElement thankYouElement = wait.until(ExpectedConditions.visibilityOf(
                    registrationPage.thankYouHeader
            ));

            // Assert the message is displayed
            Assert.assertTrue(
                    "THANK YOU message not displayed",
                    thankYouElement.isDisplayed()
            );

            // Take screenshot on SUCCESS (optional)
            takeScreenshot("registration_success_" + System.currentTimeMillis());

        } catch (Exception e) {
            // Take screenshot on FAILURE
            takeScreenshot("registration_failed_no_thank_you_message");
            throw new AssertionError("THANK YOU message verification failed: " + e.getMessage(), e);
        }
    }

    @When("Enter valid {string}, {string}, {string}, {string}, {string}, {string},  {string} registration details:")
    public void enterValidRegistrationDetails(String dateOfBirth, String firstName, String lastName,
                                              String email, String confirmEmail, String password, String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(registrationPage.dateOfBirthField)).sendKeys(dateOfBirth);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmEmailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmPasswordField)).sendKeys(confirmPassword);}

//    @Then("Click on Alert Window")
//    public void clickOnAlertWindow() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // Wait for and click OK button directly (no separate alert check needed)
//            wait.until(ExpectedConditions.elementToBeClickable(
//                    registrationPage.alertOkButton
//            )).click();
//
//        } catch (Exception e) {
//            System.out.println("Alert handling failed: " + e.getMessage());
//            // Take screenshot for debugging if needed
//        }
//    }


    @When("Enter valid {string}, {string}, {string}, {string}, {string}, {string} registration details:")
    public void enterValidRegistrationDetails(String dateOfBirth, String firstName,
                                              String email, String confirmEmail, String password, String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(registrationPage.dateOfBirthField)).sendKeys(dateOfBirth);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmEmailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmPasswordField)).sendKeys(confirmPassword);
    }

    @Given("Given Navigate to the registration page {string}")
    public void navigateToRegistrationPage(String url) {
        String actualUrl = ConfigReader.getProperty(url); // This reads from your properties file
        driver.get(actualUrl);}

    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    // Date of Birth
    @When("Enter valid date of birth {string}")
    public void enterDateOfBirth(String dob) {
        try {
            WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(By.id("dp")));
            dobField.clear();
            dobField.sendKeys(dob);
        } catch (Exception e) {
            takeScreenshot("enter_date_of_birth_failed");
            throw e;
        }
    }

    // First Name
    @When("Enter valid first name {string}")
    public void enterFirstName(String firstName) {
        try {
            WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_firstname")));
            firstNameField.clear();
            firstNameField.sendKeys(firstName);
        } catch (Exception e) {
            takeScreenshot("enter_first_name_failed");
            throw e;
        }
    }

    // Last Name (optional for your scenario)
    @When("Enter valid last name {string}")
    public void enterLastName(String lastName) {
        try {
            WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_lastname")));
            firstNameField.clear();
            firstNameField.sendKeys(lastName);
        } catch (Exception e) {
            takeScreenshot("enter_last_name_failed");
            throw e;
        }
    }

    // Email
    @When("Enter valid email {string}")
    public void enterEmail(String email) {
        try {
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_emailaddress")));
            emailField.clear();
            emailField.sendKeys(email);
        } catch (Exception e) {
            takeScreenshot("enter_email_failed");
            throw e;
        }
    }

    // Confirm Email
    @When("Enter valid confirm email {string}")
    public void enterConfirmEmail(String confirmEmail) {
        try {
            WebElement confirmEmailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_confirmemailaddress")));
            confirmEmailField.clear();
            confirmEmailField.sendKeys(confirmEmail);
        } catch (Exception e) {
            takeScreenshot("enter_confirm_email_failed");
            throw e;
        }
    }

    // Password
    @When("Enter valid password {string}")
    public void enterPassword(String password) {
        try {
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("signupunlicenced_password")));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            takeScreenshot("enter_password_failed");
            throw e;
        }
    }

    // Confirm Password
    @When("Enter valid confirm password {string}")
    public void enterConfirmPassword(String confirmPassword) {
        try {
            WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("signupunlicenced_confirmpassword")));
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys(confirmPassword);
        } catch (Exception e) {
            takeScreenshot("enter_confirm_password_failed");
            throw e;
        }
    }


    @But("Do NOT Accept Terms and Conditions")
    public void doNOTAcceptTermsAndConditions() {
    }

    @Then("See Last Name is required Text")
    public void seeLastNameIsRequiredText() {
        // Take screenshot before verification
        takeScreenshot("last_name_validation_start");

        try {
            WebElement error = registrationPage.lastNameRequiredError;
            Assert.assertTrue("Last Name error not visible", error.isDisplayed());

            // Take screenshot after success
            takeScreenshot("last_name_validation_success");
        } catch (Exception e) {
            // Take screenshot on failure
            takeScreenshot("last_name_validation_failed");
            throw e;
        }
    }

    @Then("See password confirmation error Text")
    public void seePasswordConfirmationErrorText() {
        try {
            // Wait for error to be visible
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOf(
                    registrationPage.confirmPasswordError
            ));

            // Assert error is displayed and red (optional style check)
            Assert.assertTrue("Password confirmation error not displayed",
                    errorElement.isDisplayed());
            Assert.assertEquals("Error message not red",
                    "color: red;", errorElement.getAttribute("style"));

        } catch (Exception e) {
            // Take screenshot on failure
            takeScreenshot("confirm_password_error_failed");
            throw new AssertionError("Password confirmation validation failed: " + e.getMessage(), e);
        }
    }
}
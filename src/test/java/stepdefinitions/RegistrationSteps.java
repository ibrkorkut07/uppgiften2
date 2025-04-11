package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
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

    // Screenshot method
    private void takeScreenshot(String name) {
        try {
            File folder = new File("C:\\Users\\ibrko\\OneDrive\\Desktop\\000 EC TESTER PROGRAM\\02 INTRODUCTION TO TILL PROGRAMMERING\\testAutomation_Uppgiften2\\test-output\\screenshots");
            if (!folder.exists()) {
                folder.mkdir();
            }

            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(folder, name + ".png"));

            System.out.println("Screenshot saved to: " + folder.getAbsolutePath() + "/" + name + ".png");
        } catch (Exception e) {
            System.out.println("Couldn't save screenshot: " + e.getMessage());
        }
    }

    @Before
    public void setup() {
        this.driver = Driver.getDriver();
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.registrationPage = new RegistrationPage(driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        takeScreenshot(scenario.isFailed() ? "FAILED" : "PASSED");
        Driver.quitDriver();
    }

    @After
    public void verifyJsonGenerated(Scenario scenario) {
        File report = new File("target/cucumber-reports/cucumber.json");
        System.out.println("JSON REPORT EXISTS: " + report.exists());
        System.out.println("JSON REPORT SIZE: " + report.length());
    }
//
//    @AfterAll
//    public static void verifyReportGenerated() {
//        Path report = Paths.get("target/cucumber.json");
//        System.out.println("JSON REPORT EXISTS: " + Files.exists(report));
//        System.out.println("JSON REPORT PATH: " + report.toAbsolutePath());
//    }

//    @Before
//    public void verifyReporting() {
//        System.out.println("REPORT VERIFICATION:");
//        System.out.println("Working Dir: " + new File("").getAbsolutePath());
//        File reportDir = new File("target/cucumber-reports");
//        reportDir.mkdirs();
//        System.out.println("Report Dir exists: " + reportDir.exists());
//    }

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
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    try { Thread.sleep(1000); } catch (InterruptedException e) {}

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
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                registrationPage.confirmAndJoinButton
        );
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
            WebElement thankYouElement = wait.until(ExpectedConditions.visibilityOf(
                    registrationPage.thankYouHeader
            ));

            Assert.assertTrue(
                    "THANK YOU message not displayed",
                    thankYouElement.isDisplayed()
            );
            takeScreenshot("registration_success_" + System.currentTimeMillis());

        } catch (Exception e) {
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
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmEmailField)).sendKeys(confirmEmail);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmPasswordField)).sendKeys(confirmPassword);}


    @Then("Click OK on Alert Window")
    public void clickOnAlertWindow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(
                    registrationPage.alertOkButton
            )).click();
        } catch (Exception e) {
            System.out.println("Alert handling failed: " + e.getMessage());
        }
    }

    @When("Enter valid {string}, {string}, {string}, {string}, {string}, {string} registration details:")
    public void enterValidRegistrationDetails(String dateOfBirth, String firstName,
                                              String email, String confirmEmail, String password, String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(registrationPage.dateOfBirthField)).sendKeys(dateOfBirth);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmEmailField)).sendKeys(confirmEmail);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmPasswordField)).sendKeys(confirmPassword);
    }

    @Given("Navigate to the registration page {string}")
    public void navigateToRegistrationPage(String url) {
        String actualUrl = ConfigReader.getProperty(url);
        driver.get(actualUrl);}

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
        takeScreenshot("last_name_validation_start");

        try {
            WebElement error = registrationPage.lastNameRequiredError;
            Assert.assertTrue("Last Name error not visible", error.isDisplayed());
            takeScreenshot("last_name_validation_success");
        } catch (Exception e) {
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
            Assert.assertTrue("Password confirmation error not displayed",
                    errorElement.isDisplayed());
            Assert.assertEquals("Error message not red",
                    "color: red;", errorElement.getAttribute("style"));
        } catch (Exception e) {
            takeScreenshot("confirm_password_error_failed");
            throw new AssertionError("Password confirmation validation failed: " + e.getMessage(), e);
        }
    }

    @Then("See Terms and Conditions confirmation error Text")
    public void seeTermsAndConditionsConfirmationErrorText() {
        try {
            WebElement error = registrationPage.acceptTermsMessage;
            Assert.assertTrue("Terms confirmation error not visible", error.isDisplayed());
            takeScreenshot("Terms_confirmation_success");
        } catch (Exception e) {
            takeScreenshot("Terms_confirmation_failed");
            throw e;
        }
    }

    @Then("Click on GO TO MY LOCKER button")
    public void clickOnGOTOMYLOCKERButton() {
        try {
            WebElement goToMyLockerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn red' and contains(@href, 'obstainlessingNumber')]")));
            goToMyLockerButton.click();
        } catch (Exception e) {
            takeScreenshot("enter_GoToMyLocker_failed");
            throw e;
        }

    }
}
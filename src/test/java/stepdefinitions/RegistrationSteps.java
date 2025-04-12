package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.File;
import java.time.Duration;

public class RegistrationSteps {
    private final WebDriver driver;
    private final RegistrationPage registrationPage;
    private final WebDriverWait wait;

    public RegistrationSteps() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.registrationPage = new RegistrationPage(driver);
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

    @When("Enter valid {string}, {string}, {string}, {string}, {string}, {string}, {string} registration details:")
    public void enterValidRegistrationDetails(String dateOfBirth, String firstName, String lastName,
                                              String email, String confirmEmail, String password, String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOf(registrationPage.dateOfBirthField)).sendKeys(dateOfBirth);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmEmailField)).sendKeys(confirmEmail);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.confirmPasswordField)).sendKeys(confirmPassword);
    }

    @Then("Click OK on Alert Window")
    public void clickOnAlertWindow() {
        try {
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
        driver.get(actualUrl);
    }

    @When("Enter valid date of birth {string}")
    public void enterDateOfBirth(String dob) {
        try {
            WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.dateOfBirthField));
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
            WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.firstNameField));
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
            WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.lastNameField));
            lastNameField.clear();
            lastNameField.sendKeys(lastName);
        } catch (Exception e) {
            takeScreenshot("enter_last_name_failed");
            throw e;
        }
    }

    @When("Enter valid email {string}")
    public void enterEmail(String email) {
        try {
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.emailField));
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
            WebElement confirmEmailField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.confirmEmailField));
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
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.passwordField));
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
            WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.confirmPasswordField));
            confirmPasswordField.clear();
            confirmPasswordField.sendKeys(confirmPassword);
        } catch (Exception e) {
            takeScreenshot("enter_confirm_password_failed");
            throw e;
        }
    }

    @But("Do NOT Accept Terms and Conditions")
    public void doNOTAcceptTermsAndConditions() {
        // Intentionally left empty
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
            WebElement goToMyLockerButton = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.goToMyLockerButton));
            goToMyLockerButton.click();
        } catch (Exception e) {
            takeScreenshot("enter_GoToMyLocker_failed");
            throw e;
        }
    }

    private void takeScreenshot(String name) {
        try {
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("test-output/screenshots/" + name + ".png"));
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
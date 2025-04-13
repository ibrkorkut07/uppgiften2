package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestUtils;
import org.openqa.selenium.WebDriver;
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

    // Private helper methods
    private void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void enterText(WebElement field, String text) {
        wait.until(ExpectedConditions.visibilityOf(field)).clear();
        field.sendKeys(text);
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
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
        TestUtils.scrollIntoView(driver, registrationPage.confirmAndJoinButton);

        try {
            registrationPage.confirmAndJoinButton.click();
        } catch (Exception e) {
            jsClick(registrationPage.confirmAndJoinButton);
        }
    }

    @Then("See THANK YOU FOR CREATING AN ACCOUNT Text")
    public void seeThankYouText() {
        WebElement thankYouElement = wait.until(ExpectedConditions.visibilityOf(
                registrationPage.thankYouHeader
        ));
        // Take screenshot
        TestUtils.takeScreenshot(driver, "registration_success");
        Assert.assertTrue(
                "THANK YOU message not displayed",
                thankYouElement.isDisplayed()
        );
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
        WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.dateOfBirthField
        ));
        dobField.clear();
        dobField.sendKeys(dob);
    }

    @When("Enter valid first name {string}")
    public void enterFirstName(String firstName) {
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.firstNameField
        ));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    @When("Enter valid last name {string}")
    public void enterLastName(String lastName) {
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.lastNameField
        ));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @When("Enter valid email {string}")
    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.emailField
        ));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @When("Enter valid confirm email {string}")
    public void enterConfirmEmail(String confirmEmail) {
        WebElement confirmEmailField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.confirmEmailField
        ));
        confirmEmailField.clear();
        confirmEmailField.sendKeys(confirmEmail);
    }

    @When("Enter valid password {string}")
    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.passwordField
        ));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("Enter valid confirm password {string}")
    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(
                registrationPage.confirmPasswordField
        ));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    @But("Do NOT Accept Terms and Conditions")
    public void doNOTAcceptTermsAndConditions() {
        // Do nothing
    }

    @Then("See Last Name is required Text")
    public void seeLastNameIsRequiredText() {
        TestUtils.takeScreenshot(driver,"last_name_validation_start");
        try {
            WebElement error = registrationPage.lastNameRequiredError;
            Assert.assertTrue("Last Name error not visible", error.isDisplayed());
            TestUtils.takeScreenshot(driver,"last_name_validation_success");
        } catch (Exception e) {
            TestUtils.takeScreenshot(driver,"last_name_validation_failed");
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
            TestUtils.takeScreenshot(driver,"confirm_password_error_failed");
            throw new AssertionError("Password confirmation validation failed: " + e.getMessage(), e);
        }
    }

    @Then("See Terms and Conditions confirmation error Text")
    public void seeTermsAndConditionsConfirmationErrorText() {
        try {
            WebElement error = registrationPage.acceptTermsMessage;
            Assert.assertTrue("Terms confirmation error not visible", error.isDisplayed());
            TestUtils.takeScreenshot(driver,"Terms_confirmation_success");
        } catch (Exception e) {
            TestUtils.takeScreenshot(driver,"Terms_confirmation_failed");
            throw e;
        }
    }

    @Then("Click on GO TO MY LOCKER button")
    public void clickOnGOTOMYLOCKERButton() {
        try {
            WebElement goToMyLockerButton = wait.until(ExpectedConditions.elementToBeClickable(registrationPage.goToMyLockerButton));
            goToMyLockerButton.click();
        } catch (Exception e) {
            TestUtils.takeScreenshot(driver,"enter_GoToMyLocker_failed");
            throw e;
        }
    }

        @When("Enter valid {string}, {string}, {string}, {string}, {string}, {string},  {string} registration details:")
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
}

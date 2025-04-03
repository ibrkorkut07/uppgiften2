package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import utilities.Driver;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

public class RegistrationSteps {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        // Handle single window only
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        registrationPage = new RegistrationPage(driver);
    }

//    @After
//    public void tearDown() {
//        Driver.quitDriver();
//    }

    @Given("Navigate to the registration page {string}")
    public void navigateToRegistrationPage(String url) {
        driver.get(url);
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

//    @When("Accept Code of Ethics and Conduct")
//    public void acceptCodeOfEthicsAndConduct() {
//        int maxAttempts = 5;
//        int scrollPixels = 400; // Adjust based on page structure
//
//        for (int attempt = 0; attempt < maxAttempts; attempt++) {
//            try {
//                // Try to find the element
//                WebElement codeOfConductCheckbox = driver.findElement(
//                        By.id("fammembersignup_agreetocodeofethicsandconduct")
//                );
//
//                // If found, scroll to it and click
//                new Actions(driver)
//                        .moveToElement(codeOfConductCheckbox) // Auto-scrolls into view
//                        .click()
//                        .perform();
//
//                // Verify it worked
//                if (codeOfConductCheckbox.isSelected()) {
//                    return; // Success! Exit the method
//                }
//            } catch (NoSuchElementException e) {
//                // If element not found, scroll down and retry
//                ((JavascriptExecutor) driver).executeScript(
//                        "window.scrollBy(0, arguments[0]);",
//                        scrollPixels
//                );
//                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
//            }
//        }
//        throw new RuntimeException("Checkbox not found after scrolling");
//    }
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

    @And("Click on OK on Change Your Password PopUp")
    public void clickOnOKOnChangeYourPasswordPopUp() {
        // Wait for the alert to appear (max 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Get the alert text (optional)
        System.out.println("Alert Text: " + alert.getText());

        // Click "OK" to accept the alert
        alert.accept();
    }

    @Then("See THANK YOU FOR CREATING AN ACCOUNT Text")
    public void seeThankYouText() {
        Assert.assertTrue("Thank you header is not displayed",
                registrationPage.thankYouHeader.isDisplayed());
    }

    // Negative test steps
//    @When("I enter registration details without last name")
//    public void registerWithoutLastName() {
//        registrationPage.enterRegistrationDetails("01/01/1990", "Test", "", "test@example.com", "Pass123!", "Pass123!");
//    }
//
//    @When("I enter registration details with password mismatch:")
//    public void enterMismatchedPasswords(io.cucumber.datatable.DataTable dataTable) {
//        Map<String, String> data = dataTable.asMap(String.class, String.class);
//        registrationPage.enterRegistrationDetails(
//                "01/01/1990", "Test", "User", "test@example.com",
//                data.get("Password"), data.get("Confirm Password"));
//    }

    @Then("I should see error {string}")
    public void verifyErrorMessage(String expectedError) {
        // Implement error message verification
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

    @Then("Click on Alert Window")
    public void clickOnAlertWindow() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait for and click OK button directly (no separate alert check needed)
            wait.until(ExpectedConditions.elementToBeClickable(
                    registrationPage.alertOkButton
            )).click();

        } catch (Exception e) {
            System.out.println("Alert handling failed: " + e.getMessage());
            // Take screenshot for debugging if needed
        }
    }


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


}



//package stepdefinitions;
//
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import pages.RegistrationPage;
//import utilities.Driver;
//
//import java.awt.*;
//import java.time.Duration;
//import java.util.Map;
//
//public class RegistrationSteps {
//    private WebDriver driver;
//    private RegistrationPage registrationPage;
//    private String thankYouHeader;
//    // Actions actions = new Actions(driver);
//
//    // RegistrationPage registrationPages = new RegistrationPage();
//
//    // Constructor injection
//    public RegistrationSteps() {
//        Driver DriverFactory = null;
//        this.driver = DriverFactory.getDriver(); // Get driver instance
//        this.registrationPage = new RegistrationPage(driver);
//    }
//
//    @Before
//    public void setup() {
//        // Additional setup if needed
//    }
////    private WebDriver driver;
////    private RegistrationPage registrationPage;
////
////    @Before
////    public void setUp() {
////        // Browser setup handled through hooks
////        registrationPage = new RegistrationPage(driver);
////    }
//
//    @Given("Navigate to the registration page {string}")
//    public void navigateToRegistrationPage(String url) {
//        driver.get(url);
//        waitForPageLoad();
//    }
//
//    // Happy path steps
//    @When("Enter valid {string}, {string}, {string}, {string}, {string},  {string} registration details:")
//    public void enterValidRegistrationDetails(String dateOfBirth, String firstName, String lastName, String email, String password, String confirmPassword) {
//
//        registrationPage.dateOfBirthField.sendKeys(dateOfBirth);
//        registrationPage.firstNameField.sendKeys(firstName);
//        registrationPage.lastNameField.sendKeys(lastName);
//        registrationPage.emailField.sendKeys(email);
//        registrationPage.passwordField.sendKeys(password);
//        registrationPage.confirmPasswordField.sendKeys(confirmPassword);
//    }
//
//    @When("Accept Terms and Conditions")
//    public void acceptTerms() {
//        Actions actions = new Actions(driver);
//        registrationPage.termsAcceptionBox.click();
//        actions.scrollToElement(registrationPage.confirmAndJoinButton).perform();
//    }
//
//    @And("Confirm aged over Eighteen")
//    public void confirmAgedOver() {
//        Actions actions = new Actions(driver);
//        actions.scrollToElement(registrationPage.age18ConfirmLabel).perform();
//        registrationPage.age18ConfirmLabel.click();
//    }
//
//    @Then("Accept Code of Ethics and Conduct")
//    public void acceptCodeOfEthicsAndConduct() {
//        Actions actions = new Actions(driver);
//        actions.scrollToElement(registrationPage.agreeToCodeOfConductLabel).perform();
//        registrationPage.agreeToCodeOfConductLabel.click();
//    }
//
//    @And("Click on Confirm and Join button")
//    public void clickOnConfirmAndJoinButton() {
//        Actions actions = new Actions(driver);
//        actions.scrollToElement(registrationPage.confirmAndJoinButton).perform();
//        registrationPage.confirmAndJoinButton.click();
//    }
//
//    @And("Click on OK on Change Your Password PopUp")
//    public void clickOnOKOnChangeYourPasswordPopUp() {
//        registrationPage.changePasswordOkButton.click();
//    }
//
//    @Then("See THANK YOU FOR CREATING AN ACCOUNT Text")
//    public void seeTHANKYOUFORCREATINGANACCOUNTText() {
//
//        Assert.assertTrue(thankYouHeader.contains("THANK YOU FOR CREATING AN ACCOUNT"));
//    }
//
//
//
////    String confirmationText = thankYouHeader.getText();
////Assert.assertTrue(confirmationText.contains("THANK YOU FOR CREATING AN ACCOUNT"));
//
//    @When("Click on the register button")
//    public void clickRegister() {
//        // registrationPage.clickRegisterButton();
//    }
//
//    @Then("See the successful registration message")
//    public void verifySuccessMessage() {
//        // String actualMessage = registrationPage.getSuccessMessage();
//        // Assert.assertEquals("Registration successful message not shown",
//                // "Your account has been created", actualMessage);
//    }
//
//    // Negative test steps
//    @When("I enter registration details without last name")
//    public void registerWithoutLastName() {
//        registrationPage.enterFirstName("Test");
//        registrationPage.enterEmail("test@example.com");
//        registrationPage.enterPassword("Pass123!");
//        registrationPage.enterConfirmPassword("Pass123!");
//        // registrationPage.acceptTermsCheckbox();
//    }
//
//    @When("I enter registration details with password mismatch:")
//    public void enterMismatchedPasswords(Map<String, String> data) {
//        registrationPage.enterFirstName("Test");
//        registrationPage.enterLastName("User");
//        registrationPage.enterEmail("test@example.com");
//        registrationPage.enterPassword(data.get("Password"));
//        registrationPage.enterConfirmPassword(data.get("Confirm Password"));
//        // registrationPage.acceptTermsCheckbox();
//    }
//
//    @When("I don't accept terms and conditions")
//    public void doNotAcceptTerms() {
//        // Intentionally left blank as terms are not accepted
//    }
//
//    @Then("I should see error {string}")
//    public void verifyErrorMessage(String expectedError) {
//        // String actualError = registrationPage.getErrorMessage();
//        // Assert.assertEquals("Error message not as expected", expectedError, actualError);
//    }
//
//    // Private method with explicit wait
//    private void waitForPageLoad() {
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(d -> ((JavascriptExecutor) d)
//                        .executeScript("return document.readyState").equals("complete"));
//    }
//
//    @And("Logged in")
//    public void iShouldBeLoggedIn() {
//    }
//
//    @Then("Click on Create A New Account button")
//    public void clickOnCreateANewAccountButton() {
//        registrationPage.createANewAccount.click();
//    }
//
//    @Then("Click on Create An Account button")
//    public void clickOnCreateAnAccountButton() {
//        registrationPage.createAccountButton.click();
//    }
//
//
//
//}

package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import utilities.Driver;

import java.time.Duration;
import java.util.Map;

public class RegistrationSteps {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    // Constructor injection
    public RegistrationSteps() {
        Driver DriverFactory = null;
        this.driver = DriverFactory.getDriver(); // Get driver instance
        this.registrationPage = new RegistrationPage(driver);
    }

    @Before
    public void setup() {
        // Additional setup if needed
    }
//    private WebDriver driver;
//    private RegistrationPage registrationPage;
//
//    @Before
//    public void setUp() {
//        // Browser setup handled through hooks
//        registrationPage = new RegistrationPage(driver);
//    }

    @Given("Navigate to the registration page {string}")
    public void navigateToRegistrationPage(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    // Happy path steps
    @When("I enter valid registration details:")
    public void enterValidDetails(Map<String, String> data) {
        registrationPage.enterFirstName(data.get("First Name"));
        registrationPage.enterLastName(data.get("Last Name"));
        registrationPage.enterEmail(data.get("Email"));
        registrationPage.enterPassword(data.get("Password"));
        registrationPage.enterConfirmPassword(data.get("Confirm Password"));
    }

    @When("I accept terms and conditions")
    public void acceptTerms() {
        registrationPage.acceptTermsCheckbox();
    }

    @When("I click the register button")
    public void clickRegister() {
        registrationPage.clickRegisterButton();
    }

    @Then("I should see the registration success message")
    public void verifySuccessMessage() {
        String actualMessage = registrationPage.getSuccessMessage();
        Assert.assertEquals("Registration successful message not shown",
                "Your account has been created", actualMessage);
    }

    // Negative test steps
    @When("I enter registration details without last name")
    public void registerWithoutLastName() {
        registrationPage.enterFirstName("Test");
        registrationPage.enterEmail("test@example.com");
        registrationPage.enterPassword("Pass123!");
        registrationPage.enterConfirmPassword("Pass123!");
        registrationPage.acceptTermsCheckbox();
    }

    @When("I enter registration details with password mismatch:")
    public void enterMismatchedPasswords(Map<String, String> data) {
        registrationPage.enterFirstName("Test");
        registrationPage.enterLastName("User");
        registrationPage.enterEmail("test@example.com");
        registrationPage.enterPassword(data.get("Password"));
        registrationPage.enterConfirmPassword(data.get("Confirm Password"));
        registrationPage.acceptTermsCheckbox();
    }

    @When("I don't accept terms and conditions")
    public void doNotAcceptTerms() {
        // Intentionally left blank as terms are not accepted
    }

    @Then("I should see error {string}")
    public void verifyErrorMessage(String expectedError) {
        String actualError = registrationPage.getErrorMessage();
        Assert.assertEquals("Error message not as expected", expectedError, actualError);
    }

    // Private method with explicit wait
    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> ((JavascriptExecutor) d)
                        .executeScript("return document.readyState").equals("complete"));
    }

    @And("I should be logged in")
    public void iShouldBeLoggedIn() {
    }
}

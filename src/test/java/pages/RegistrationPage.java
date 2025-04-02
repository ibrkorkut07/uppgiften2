package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(id = "firstName") private WebElement firstNameField;
    @FindBy(id = "lastName") private WebElement lastNameField;
    @FindBy(id = "email") private WebElement emailField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(id = "confirmPassword") private WebElement confirmPasswordField;
    @FindBy(id = "termsCheckbox") private WebElement termsCheckbox;
    @FindBy(id = "registerButton") private WebElement registerButton;
    @FindBy(className = "success-message") private WebElement successMessage;
    @FindBy(className = "error-message") private WebElement errorMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Interaction methods
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField)).sendKeys(password);
    }

    public void acceptTermsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}

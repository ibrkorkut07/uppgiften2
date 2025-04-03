package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    public WebDriver driver;
    public WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//a[text()='CREATE A NEW ACCOUNT']") public WebElement createANewAccount;
    @FindBy(xpath = "//a[contains(., 'CREATE AN ACCOUNT')]") public WebElement createAccountButton;
    @FindBy(id = "dp") public WebElement dateOfBirthField;
    @FindBy(id = "member_firstname") public WebElement firstNameField;
    @FindBy(id = "member_lastname") public WebElement lastNameField;
    @FindBy(id = "member_emailaddress") public WebElement emailField;
    @FindBy(id = "signupunlicenced_password") public WebElement passwordField;
    @FindBy(id = "signupunlicenced_confirmpassword") public WebElement confirmPasswordField;
    @FindBy(css = "label[for='sign_up_25']") public WebElement termsAcceptionBox;
    @FindBy(css = "label[for='sign_up_26']") public WebElement age18ConfirmLabel;
    // @FindBy(xpath = "//label[@for='fannembersignup_agreetocodeofethicsandconduct']") public WebElement codeOfConductLabel;
    @FindBy(css = "span.inc") public WebElement codeOfConductLabel;
    @FindBy(id = "fammembersignup_agreetocodeofethicsandconduct") public WebElement codeOfConductCheckbox;
    // Simple XPath
    // @FindBy(xpath = "//span[@class='inc']") public WebElement codeOfConductLabel;

    // If nested in a specific structure
    // @FindBy(xpath = "//div[@class='md-checkbox']//span[@class='inc']") public WebElement codeOfConductLabel;

    // Most direct locator
    // @FindBy(css = "span.inc") public WebElement codeOfConductLabel;

    // If you need the parent checkbox context
    // @FindBy(xpath = "//span[@class='inc']/preceding-sibling::input[@type='checkbox']") public WebElement codeOfConductLabel;


    // @FindBy(name = "join") public WebElement confirmAndJoinButton;
    // @FindBy(xpath = "//label[contains(.,'I have read')]/input") public WebElement codeOfConductCheckbox;
    @FindBy(xpath = "//button[contains(.,'CONFIRM AND JOIN')]") public WebElement confirmAndJoinButton;
    @FindBy(xpath = "//button[contains(., 'OK')]") public WebElement changePasswordOkButton;
    @FindBy(xpath = "//h2[contains(@class, 'bold') and contains(text(), 'THANK YOU FOR CREATING AN ACCOUNT')]") public WebElement thankYouHeader;

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

    public void enterValidRegistrationDetails(String dateOfBirth, String firstName, String lastName, String email, String password, String confirmPassword) {
    }

//    public void acceptTermsCheckbox() {
//        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
//    }
//
//    public void clickRegisterButton() {
//        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
//    }
//
//    public String getSuccessMessage() {
//        return wait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
//    }
//
//    public String getErrorMessage() {
//        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
//    }
}
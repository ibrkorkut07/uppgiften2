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
    @FindBy(xpath = "//span[@for='member_lastname' and contains(text(),'Last Name is required')]")
    public WebElement lastNameRequiredError;
    @FindBy(id = "member_emailaddress") public WebElement emailField;
    @FindBy(id = "member_confirmemailaddress") public WebElement confirmEmailField;
    @FindBy(id = "signupunlicenced_password") public WebElement passwordField;
    @FindBy(id = "signupunlicenced_confirmpassword") public WebElement confirmPasswordField;
    @FindBy(css = "span[data-valmsg-for='ConfirmPassword'].field-validation-error")
    public WebElement confirmPasswordError;
    @FindBy(css = "label[for='sign_up_25']") public WebElement termsAcceptionBox;
    @FindBy(css = "label[for='sign_up_26']") public WebElement age18ConfirmLabel;
    @FindBy(xpath = "//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/label") public WebElement codeOfConductCheckbox;
    @FindBy(xpath = "//h2[contains(text(), 'Change your password')]") public WebElement changePasswordAlert;
    @FindBy(xpath = "//button[contains(text(), 'OK') or contains(text(), 'Continue')]") public WebElement alertOkButton;
    @FindBy(name = "join") public WebElement confirmAndJoinButton;
    @FindBy(xpath = "//button[contains(., 'OK')]") public WebElement changePasswordOkButton;
    @FindBy(xpath = "//h2[contains(@class, 'bold') and contains(text(), 'THANK YOU FOR CREATING AN ACCOUNT')]")
    public WebElement thankYouHeader;
    @FindBy(xpath = "//span[@for='TermsAccept' and contains(text(), 'You must confirm that you have read and accepted our Terms and Conditions')]")
    public WebElement acceptTermsMessage;
    @FindBy(xpath = "//a[@class='btn red' and contains(@href, 'obstainlessingNumber')]")
    public WebElement goToMyLockerButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


}
package pages;  // Organizes related classes into the 'pages' package

// Import required Selenium and Java classes
import org.openqa.selenium.WebDriver;  // Main Selenium WebDriver interface
import org.openqa.selenium.WebElement;  // Represents HTML elements
import org.openqa.selenium.support.FindBy;  // Annotation for locating elements
import org.openqa.selenium.support.PageFactory;  // Initializes Page Object elements
import org.openqa.selenium.support.ui.ExpectedConditions;  // Conditions for explicit waits
import org.openqa.selenium.support.ui.WebDriverWait;  // Explicit wait mechanism
import java.time.Duration;  // Time unit for waits

public class RegistrationPageEXP {
    // WebDriver instance to interact with browser
    public WebDriver driver;

    // Explicit wait for synchronizing with page elements
    public WebDriverWait wait;

    // LOCATORS SECTION:
    // @FindBy annotations locate elements when the page is loaded

    // Finds "CREATE A NEW ACCOUNT" link by exact text match
    @FindBy(xpath = "//a[text()='CREATE A NEW ACCOUNT']")
    public WebElement createANewAccount;

    // Finds "CREATE AN ACCOUNT" button by partial text match
    @FindBy(xpath = "//a[contains(., 'CREATE AN ACCOUNT')]")
    public WebElement createAccountButton;

    // Finds date picker field by its ID
    @FindBy(id = "dp")
    public WebElement dateOfBirthField;

    // Finds first name field by ID
    @FindBy(id = "member_firstname")
    public WebElement firstNameField;

    // Finds last name field by ID
    @FindBy(id = "member_lastname")
    public WebElement lastNameField;

    // Finds last name error message using XPath with multiple conditions
    @FindBy(xpath = "//span[@for='member_lastname' and contains(text(),'Last Name is required')]")
    public WebElement lastNameRequiredError;

    // Finds email field by ID
    @FindBy(id = "member_emailaddress")
    public WebElement emailField;

    // Finds confirm email field by ID
    @FindBy(id = "member_confirmemailaddress")
    public WebElement confirmEmailField;

    // Finds password field by ID
    @FindBy(id = "signupunlicenced_password")
    public WebElement passwordField;

    // Finds confirm password field by ID
    @FindBy(id = "signupunlicenced_confirmpassword")
    public WebElement confirmPasswordField;

    // Finds password error message using CSS selector with class
    @FindBy(css = "span[data-valmsg-for='ConfirmPassword'].field-validation-error")
    public WebElement confirmPasswordError;

    // Finds terms checkbox using CSS selector
    @FindBy(css = "label[for='sign_up_25']")
    public WebElement termsAcceptionBox;

    // Finds age confirmation checkbox
    @FindBy(css = "label[for='sign_up_26']")
    public WebElement age18ConfirmLabel;

    // Finds code of conduct label by CSS class
    @FindBy(css = "span.inc")
    public WebElement codeOfConductLabel;

    // Finds code of conduct checkbox using absolute XPath (less recommended)
    @FindBy(xpath = "//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/label")
    public WebElement codeOfConductCheckbox;

    // Finds password change alert by text content
    @FindBy(xpath = "//h2[contains(text(), 'Change your password')]")
    public WebElement changePasswordAlert;

    // Finds generic OK button in alerts
    @FindBy(xpath = "//button[contains(text(), 'OK') or contains(text(), 'Continue')]")
    public WebElement alertOkButton;

    // Finds join button by name attribute
    @FindBy(name = "join")
    public WebElement confirmAndJoinButton;

    // Finds OK button specifically for password change
    @FindBy(xpath = "//button[contains(., 'OK')]")
    public WebElement changePasswordOkButton;

    // Finds thank you header with specific class and text
    @FindBy(xpath = "//h2[contains(@class, 'bold') and contains(text(), 'THANK YOU FOR CREATING AN ACCOUNT')]")
    public WebElement thankYouHeader;

    // Finds terms acceptance error message
    @FindBy(xpath = "//span[@for='TermsAccept' and contains(text(),'You must confirm that you have read and accepted our Terms and Conditions')]")
    public WebElement acceptTermsMessage;

    // Finds "GO TO MY LOCKER" button with specific class and href
    @FindBy(xpath = "//a[@class='btn red' and contains(@href, 'obstainlessingNumber')]")
    public WebElement goToMyLockerButton;

    // Constructor initializes the page object
    public RegistrationPageEXP(WebDriver driver) {
        this.driver = driver;  // Assigns the WebDriver instance
        // Sets up explicit wait with 10 second timeout
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initializes all @FindBy elements
        PageFactory.initElements(driver, this);
    }

    // Method to safely enter first name with wait
    public void enterFirstName(String firstName) {
        // Waits for element to be visible before interacting
        wait.until(ExpectedConditions.visibilityOf(firstNameField))
                .sendKeys(firstName);  // Enters the text
    }
}
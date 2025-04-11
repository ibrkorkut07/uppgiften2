@registration
Feature: Basketball England New Supporter Registration

  Background:
    Given Navigate to the registration page "BasEngUrl"
    Then Click on Create A New Account button
    Then Click on Create An Account button

  @positiveScenario @chrome @firefox
  Scenario Outline: Successful registration with valid credentials
    When Enter valid "<dateOfBirth>", "<firstName>", "<lastName>", "<email>", "<confirmEmail>", "<password>",  "<confirmPassword>" registration details:
    And Accept Terms and Conditions
    And Confirm aged over Eighteen
    Then Accept Code of Ethics and Conduct
    And Click on Confirm and Join button
    Then Click OK on Alert Window
    Then See THANK YOU FOR CREATING AN ACCOUNT Text
    Then Click on GO TO MY LOCKER button

    Examples:
      | dateOfBirth| firstName | lastName | email                 | confirmEmail          |password  | confirmPassword |
      | 01/12/1999 | James     | Smithy   | james.smithy@test.com | james.smithy@test.com |Pass12a!  | Pass12a!        |
      | 01/12/1999 | James     |          | james.smithy@test.com | james.smithy@test.com |Pass12a!  | Pass12a!        |
      | 01/12/1999 | James     | Smithy   | james.smithy@test.com | james.smithy@test.com |Pass12a!  | Pass12b!        |
    
  @acceptTermsValidation
  Scenario: Registration without accepting Terms and Conditions
    When Enter valid date of birth "01/11/2002"
    And Enter valid first name "James"
    And Enter valid last name "Johns"
    And Enter valid email "test@example.com"
    And Enter valid confirm email "test@example.com"
    And Enter valid password "Pass123!"
    And Enter valid confirm password "Pass123!"
    But Do NOT Accept Terms and Conditions
    And Confirm aged over Eighteen
    Then Accept Code of Ethics and Conduct
    And Click on Confirm and Join button
    Then See Terms and Conditions confirmation error Text
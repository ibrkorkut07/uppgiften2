@registration
Feature: Basketball England New Supporter Registration

  Background:
    Given Navigate to the registration page "https://membership.basketballengland.co.uk/"
    Then Click on Create A New Account button
    Then Click on Create An Account button

  @validation @chrome
  Scenario Outline: Registration without accepting terms
    When Enter valid "<dateOfBirth>", "<firstName>", "<lastName>", "<email>", "<confirmEmail>", "<password>",  "<confirmPassword>" registration details:
    And Accept Terms and Conditions
    And Confirm aged over Eighteen
    Then Accept Code of Ethics and Conduct
    And Click on Confirm and Join button
    Then Click on Alert Window
    # And Click on OK on Change Your Password PopUp
    Then See THANK YOU FOR CREATING AN ACCOUNT Text

    Examples:
      | dateOfBirth | firstName | lastName | email                 | confirmEmail          | password | confirmPassword |
      | 01/11/2002  | James     | Smithy   | james.smithy@test.com | james.smithy@test.com | Pass12a! | Pass12a!        |
    But I don't accept terms and conditions
    And I click the register button
    Then I should see error "You must accept terms and conditions"
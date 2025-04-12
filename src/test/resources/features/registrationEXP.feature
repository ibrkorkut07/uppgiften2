# Feature file using Gherkin syntax
@registrationEXP  # Tag for grouping related scenarios
Feature: Basketball England New Supporter Registration

  # Background runs before each scenario in this feature
  Background:
    # Navigates to registration page using URL from config
    Given Navigate to the registration page "BasEngUrl"

    # Clicks initial create account buttons
    Then Click on Create A New Account button
    Then Click on Create An Account button

  # Positive test case with example data
  @positiveScenario @chrome @firefox  # Tags for filtering
  Scenario Outline: Successful registration with valid credentials
    # Enters all registration details from examples table
    When Enter valid "<dateOfBirth>", "<firstName>", "<lastName>", "<email>", "<confirmEmail>", "<password>", "<confirmPassword>" registration details:

    # Accepts required agreements
    And Accept Terms and Conditions
    And Confirm aged over Eighteen
    Then Accept Code of Ethics and Conduct

    # Completes registration
    And Click on Confirm and Join button
    Then Click OK on Alert Window

    # Verification points
    Then See THANK YOU FOR CREATING AN ACCOUNT Text
    Then Click on GO TO MY LOCKER button

    # Test data examples (can be extended)
    Examples:
      | dateOfBirth| firstName | lastName | email                 | confirmEmail          |password  | confirmPassword |
      | 01/12/1999 | James     | Smithy   | james.smithy@test.com | james.smithy@test.com |Pass12a!  | Pass12a!        |

  # Negative test case for validation
  @validation @chrome
  Scenario: Registration validation tests
    # Steps for validation testing...
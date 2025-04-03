@registration
Feature: Basketball England New Supporter Registration
  As a new user
  I want to register on Basketball England website
  So that I can access supporter features

  Background:
    Given Navigate to the registration page "https://membership.basketballengland.co.uk/"
    Then Click on Create A New Account button
    Then Click on Create An Account button

  @happy-path @chrome @firefox
  Scenario Outline: Successful registration with valid credentials
    When Enter valid "<dateOfBirth>", "<firstName>", "<lastName>", "<email>", "<password>",  "<confirmPassword>" registration details:
    And Accept Terms and Conditions
    And Confirm aged over Eighteen
    Then Accept Code of Ethics and Conduct
    And Click on Confirm and Join button
    And Click on OK on Change Your Password PopUp
    Then See THANK YOU FOR CREATING AN ACCOUNT Text

    Examples:
      | dateOfBirth| firstName | lastName | email                 | password  | confirmPassword |
      | 01/11/2011 | John      | Smith    | john.smith@test.com   | Pass123!  | Pass123!        |
      #| 13/08/1970 | Sarah     | Jones    | sarah.jones@test.org  | Test456!  | Test456!        |

  @validation @chrome
  Scenario: Registration with missing last name
    When I enter registration details without last name
    And I click the register button
    Then I should see error "Last name is required"

  @validation @firefox
  Scenario: Registration with password mismatch
    When I enter registration details with password mismatch:
      | Password | Confirm Password |
      | Pass123! | WrongPass123!    |
    And I click the register button
    Then I should see error "Passwords do not match"

  @validation @chrome
  Scenario: Registration without accepting terms
    When I enter valid registration details:
      | First Name | Last Name | Email             | Password  | Confirm Password |
      | Mike       | Brown     | mike.brown@test.uk | Pass789!  | Pass789!         |
    But I don't accept terms and conditions
    And I click the register button
    Then I should see error "You must accept terms and conditions"
@registration
Feature: Basketball England New Supporter Registration
  As a new user
  I want to register on Basketball England website
  So that I can access supporter features

  Background:
    Given Navigate to the registration page "https://membership.basketballengland.co.uk/NewSupporterAccount"
    Then I click the register button

  @happy-path @chrome @firefox
  Scenario Outline: Successful registration with valid credentials
    When I enter valid registration details:
      | First Name | <firstName> |
      | Last Name  | <lastName>  |
      | Email      | <email>     |
      | Password   | <password>  |
      | Confirm Password | <confirmPassword> |
    And I accept terms and conditions
    And I click the register button
    Then I should see the registration success message
    And I should be logged in

    Examples:
      | firstName | lastName | email                 | password  | confirmPassword |
      | John      | Smith    | john.smith@test.com   | Pass123!  | Pass123!        |
      | Sarah     | Jones    | sarah.jones@test.org  | Test456!  | Test456!        |

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
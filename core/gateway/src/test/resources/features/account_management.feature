Feature: Account Management
  Background:
    Given a user wants to create an account

  Scenario: User provides correct details
    When the seller creates an account with the following details:
      | firstName | lastName | dob        | email           | gender | country |
      | Arnold    | Opiyo    | 2022-01-01 | arnold@me.com   | male   | Kenya   |
    Then the account should be created successfully

  Scenario: User provides incorrect email format
    When the seller provides an invalid email format, for example "arnold.com" or "arnold@me"
    Then the system should respond with an error indicating that the email format is invalid
    And the account should not be created






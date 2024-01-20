Feature: Account Management
  As a user, I need to be able to create an account with my preferred role
  Scenario: A User sends a request to create an account
    When a user sends a requets to create an account with the following details:
      | first_name | last_name | date_of_birth | email_address              | gender | country | role  |
      | Arnold     | Opiyo     | 0001=01-01    | user.example@adventure.com | Male   | Kenyan  | Buyer |
    Then the request should be successful
    And the response body should contain the message "Welcome to Soko!"





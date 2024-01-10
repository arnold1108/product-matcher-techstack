Feature: Cart
  Background:
    Given a buyer with id "123" exists

  Scenario: Buyer adds a product to cart
    Given a product with id "234" exists
    When the buyer adds the product to the cart with quantity 3
    Then the product should be added to the cart with quantity 3 successfully

  Scenario: Buyer removes producr from the cart
    Given a product with id "976" is in the cart of the buyer
    When the buyer removes the product from the cart
    Then the product should be removed from the cart successfully

  Scenario: Buyer checks out the cart
    Given the cart of the buyer with ID "123" is not empty
    When the buyer checks out the cart
    Then the buyer should receive a confirmation message "Enjoy!"


  Scenario: Buyer views their cart
    Given the cart of the buyer with ID "123" contains the following items:
      | productId                              | productName        | quantity | price  |
      | 123e4567-e89b-12d3-a456-426614174001   | Smartwatch XYZ     | 2        | 150.0  |
      | 123e4567-e89b-12d3-a456-426614174002   | Laptop ABC         | 1        | 1200.0 |
      | 123e4567-e89b-12d3-a456-426614174003   | Coffee Maker       | 3        | 80.0   |
    When the buyer views their cart
    Then the buyer should see the cart with the following details:
      | productId                              | productName        | quantity | price  |
      | 123e4567-e89b-12d3-a456-426614174001   | Smartwatch XYZ     | 2        | 150.0  |
      | 123e4567-e89b-12d3-a456-426614174002   | Laptop ABC         | 1        | 1200.0 |
      | 123e4567-e89b-12d3-a456-426614174003   | Coffee Maker       | 3        | 80.0   |
    And the buyer should see the total items in the cart as "6"
    And the buyer should see the total price of the cart as "1770.0"



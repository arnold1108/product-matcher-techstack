Feature: Explore Products
  Background:
    Given a buyer with id "123" exists

  Scenario: The buyer explores products
    When the buyer explores recommended products
    Then the buyer should receive the following recommended products:
      | productId                              | productName        | productCategory | productDescription                           | price       |
      | 123e4567-e89b-12d3-a456-426614174001   | Smartwatch XYZ     | Electronics     | Advanced smartwatch with health features     | KES 150.00  |
      | 123e4567-e89b-12d3-a456-426614174002   | Laptop ABC         | Electronics     | High-performance laptop with SSD             | KES 1200.00 |
      | 123e4567-e89b-12d3-a456-426614174003   | Coffee Maker       | Appliances      | Espresso coffee maker with frother           | KES 80.00   |

  Scenario: The Buyer likes a product
    Given a product with ID "456" exists
    When the buyer likes the product
    Then the product should receive a like

  Scenario: The Buyer adds a product to cart
    Given a product with ID "456" exists
    When the buyer adds the product with ID "789" to the cart with quantity 3
    Then the product with ID "789" should be added to the cart of the buyer with ID "456" with quantity 3


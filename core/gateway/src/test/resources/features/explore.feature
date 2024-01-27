Feature: Explore Products

  Scenario: The Buyer Explores Products
    As a buyer, I want to be able to explore products in the marketplace
    When the buyer of id "buyer_id" sends a request to explore products in the marketplace
    Then the explore request should be successful
    And the explore response should have the following details:
      | productId        | productName        | productCategory | productDescription                           | price       |
      | <product_id_1>   | Smartwatch XYZ     | Electronics     | Advanced smartwatch with health features     | KES 150.00  |
      | <product_id_2>   | Laptop ABC         | Electronics     | High-performance laptop with SSD             | KES 1200.00 |
      | <product_id_3>   | Coffee Maker       | Appliances      | Espresso coffee maker with frother           | KES 80.00   |

  Scenario: The Buyer Likes a Product
    As a buyer, I want  to be able to like a product that appeals to me
    When a buyer of id "buyer_id" sends a request to like a product of id "product_id"
    Then the explore request should be successful
    And the explore response body should contain the message "Product Liked"

  Scenario: The Buyer Adds Product to Cart
    As a buyer, I want to be able to to add a product that want to buy to cart
    When the buyer of id "buyer_id" sends a request to add 2 items of a product of id "product_id" to cart
    Then the explore request should be successful
    And the explore response body should contain the message "Product added to cart"

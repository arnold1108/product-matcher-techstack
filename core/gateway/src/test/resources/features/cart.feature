Feature: Cart

  Scenario: Buyer Views Products in Cart
    As a buyer, I want to be able to view the products in my cart
    When a buyer with id <buyer_id> sends a request to view their cart
    Then the request should be successful
    And the response should have the following details:
    | buyerId    | cartItems                                                                                     | totalItems | totalPrice |
    | <buyer_id> | {"ProductId": <product_id>, "productName", " Smartwatch XYZ", "quantity": 2, "price": 150.00} | 1          | 150.00     |

  Scenario: Buyer Removes Product from Cart
    As a buyer, I want to be able to remove a product from the cart when I want to
    When the buyer of id <buyerId> sends a request to remove a product of id <productId> from the cart
    Then the request should be successful
    And the response body should contain the message "Removed product of id <productId> from the cart"

  Scenario: Buyer Checks Out of the Cart
    As a buyer, I want to be able to check out of the cart after I have selected my products
    When a buyer of id <buyer_id> sends a request to checkout
    Then the request should be successful
    And the response body should contain the message "Enjoy!"


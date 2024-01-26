Feature: Seller manages store and stock

  Scenario: Seller Creates a new Store
    When the seller of id "seller_id" sends a request to create a new store named "MyStore" belonging to the category "Sports"
    Then the request should be successful
    And the response body should contain the message "MyStore created successfully"

  Scenario: Seller Adds stock to the Store
    When the seller of id "seller_id" owning a store of id "store_id" adds stock with the following details:
      | product_name | product_category | product_description          | quantity | price |
      | Vitron TV    | Electronics      | 32 Inch Frameless Android TV | 5        | 24000 |
    Then the product should be added successfully
    And the response body should contain the message "product_name Added"


  Scenario: Seller Managers Their Store





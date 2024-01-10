Feature: Seller manages store and stock
  Background:
    Given a seller with ID "123" exists

  Scenario: Seller Creates a new Store
    When the seller creates a new store named "MyStore" belonging to the category "StoreCategory"
    Then the store should be created successfully

  Scenario: Seller adds stock to the store
    Given the store "MyStore" exists
    When the seller adds stock with the following details:
      | productName | productCategory  | productDescription            | Quantity|price |
      | Vitron TV   | Electronics      | 32 Inch Frameless Android TV  | 5       |24000 |
    Then the product should be added successfully

  Scenario: Seller manages their store
    Given the store "MyStore" exists
    When the seller manages their store
    Then the seller should see the store details:
      | StoreName |
      | MyStore   |
    And the seller should see the following products in the store:
      | productName | remainingQuantity | likes | price | timeAdded          |
      | product1    |5                  |4      | 560   |2022-04-12 15:34:31 |
      | product2    |12                 |33     |1200   |2024-01-10 11:02:44 |
      | product3    |1                  |3      |3000   |2020-11-01 08:30:06 |





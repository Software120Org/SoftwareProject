Feature: Add new order

  Scenario: add order with two product for exist customer
    Given a customer with email "reema@gmail.com" and id=5
    And enter the product id1 =1
    And enter the product id2 =4
    When the customer orders the two products
    Then the order should have a total price of 109.98
    And a unique order Id will generated to the order
    And the order added successfully



  Scenario: add order with two product for new customer
    Given a customer with email "Lara@gmail.com" and id=25
    And enter the product id1 =1
    And enter the product id2 =4
    When the customer orders the two products
    Then I should add the customer details
    And the order should have a total price of 109.98
    And a unique order Id will generated to the order
    And the order added successfully
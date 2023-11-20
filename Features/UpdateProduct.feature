Feature: update product information


  Scenario: change the name of the product
    Given that the admin choose to update product info
    When admin select to update name
    And  admin enter the new name = "new product"
    Then the product name will updated successfully



  Scenario: change the description of the product
    Given that the admin choose to update product info
    When admin select to update description
    And  admin enter the new description = "new description"
    Then the product description will updated successfully



  Scenario: change the category of the product
    Given that the admin choose to update product info
    When admin select to update category
    And  admin enter the new category = "Interior"
    Then the product category will updated successfully



  Scenario: change the price of the product
    Given that the admin choose to update product info
    When admin select to update price
    And  admin enter the new price = "200.0"
    Then the product price will updated successfully



  Scenario: change the availability of the product
    Given that the admin choose to update product info
    When admin select to update availability
    And  admin enter the new availability = "true"
    Then the product availability will updated successfully
Feature: Add new product
  the admin is want to add new product with it's name, description, category, price, availability, category

  Scenario: product added successfully
    Given that the product isn't added yet
    When the admin enter the product name = "seat cover"
    And  the admin enter the product description="comfort and durability"
    And  the admin enter the product category="Interior"
    And  the admin enter the product price="100"
    And  the admin enter the product availability="false"
    Then the system generate a unique ID for the product
    Then the product will be added
Feature: search for any product
  the admin is want to search product with name

  Scenario: product search successfully
    Given admin choose search product
    When  admin enter the product name = "seat cover"
    Then the product will shown
Feature: update Customer information



  Scenario: change the address of the customer
    Given that I choose to update customer information
    When I select to update my address then I enter my new address = "Hebron"
    Then my address will updated successfully


  Scenario: change the phone of the customer
    Given that I choose to update customer information
    When I select to update my phone number then I enter my new phone number = "0599412633"
    Then my phone number will updated successfully


  Scenario: change the password of the customer
    Given that I choose to update customer information
    When I select to update my password then I enter my new password = "anne123"
    Then my password will updated successfully


Feature: Delete Customer

  Scenario: delete completed successfully
    Given that the admin choose to delete customer
    When the admin entered customer ID is exist
    Then Customers will be deleted



  Scenario: fail to delete
    Given that the admin choose to delete customer
    When the admin entered customer ID doesn't exist in the recorded customers
    Then the message that the customer doesn't exist will be printed

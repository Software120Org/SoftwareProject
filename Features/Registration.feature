Feature: record customer information


  Scenario: record done successfully
    Given that I enter customer name="wafaa"
    And customer email="wafaa2@gmail.com"
    And customer phone="0593650775"
    And customer address="Nablus"
    And customer password="0000"
    When I chose to record new customer
    Then a unique customer ID will be generated for the customer# And the customer will recorded successfully
    And confirmation email will be sent to customer

  Scenario: record with taken email
    Given that I enter customer name="ali"
    And customer email="ali@gmail.com"
   And customer phone="0592110142"
   And customer address="Nablus"
    And customer password="0123456"
    When I chose to record new customer
   Then I show a message that the email is already taken
   And I have option to reenter new email
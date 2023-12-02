Feature: Installation Requests
  Scenario: Installation request done successfully
    Given that the user chooses to request an installation
    And enters Product = "Car Lighting"
    And enters car make="BMW"
    And enters car model="3 Series"
    And user email="anwar5@gmail.com"
    And Enter the ID for the available installation appointment
    Then a unique ID should be generated for the Installation request
    And the Installation request should be recorded successfully
    And confirmation email should be sent to the customer
#And the email should contain the scheduled time and location

 Scenario: Record with unavailable date
    Given that the user chooses to request an installation
    And enters Product = "GPS Navigation Systems"
    And enters car make="Toyota"
    And enters car model="Camry"
    And user email="wafaa@gmail.com"
    And Enter the ID for the unavailable installation appointment
    Then a message should be displayed indicating that this id is not available

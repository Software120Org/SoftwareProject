Feature: add an installation date

  Scenario: installation date added successfully
    Given that the Date isn't added yet
    When the admin enter the Date = "2020-08-08"
    And the admin enter the Time = "07:10 AM"
    Then the system generate a unique ID for the installation date
    Then the installation date will be added










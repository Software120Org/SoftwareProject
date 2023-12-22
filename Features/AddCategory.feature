Feature: add categories

  Scenario: category added successfully
    Given that the category isn't added yet
    When the admin enter the category name = "digital"
    Then the system generate a unique ID for the Category
    Then the category will be added



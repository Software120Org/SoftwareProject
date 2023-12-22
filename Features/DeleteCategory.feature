Feature:  delete category
  Scenario: category deleted successfully
    Given that the admin choose to delete category
    When the entered category id is exist
    Then the category will deleted



  Scenario: delete fail
    Given that the admin choose to delete category
    When the entered category id is not exist
    Then the msg that the category not exist will be shown




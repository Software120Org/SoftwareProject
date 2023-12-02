Feature:  delete installation date
  Scenario: installation date deleted successfully
    Given that the admin choose to delete installation date
    When the entered installation date id is exist
    Then the installation date will deleted



  Scenario: delete fail
    Given that the admin choose to delete installation date
    When the entered installation date id is not exist
    Then the msg that the installation date not exist will be shown
Feature: Verify JP Morgan logo

  @regression
  Scenario Outline: Verify JP Morgan logo
    Given open browser and go to google.com
    When enter "<searchtext>" on search box
    And click on search button
    And user should be able to see the results
    And click on the first result
    Then verify that J.P. Morgan logo is shown
    Examples:
      |searchtext  |
      |J.P. Morgan  |
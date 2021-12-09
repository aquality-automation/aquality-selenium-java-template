Feature: Demo

  @demo
  Scenario: Empty email validation is working on Contact Us page
    Given Main page is opened
    When I open Contact us page
    Then Contact us page is opened
    When I fill contact form using following data:
      | Name     | Peter Parker             |
      | Company  | Aquality Automation      |
      | Email    | aquality-auto@a1qa.com   |
      | Phone    | +44 208 816 7320         |
      | Comment  | I'd like to contact you! |
    And I accept Privacy and Cookies Policy
    And I click Send button
    Then Notification about empty fields is present

  @demo
  Scenario Outline: ScenarioContext demo
    When I store '<value1>' as 'value1'
    And I store '<value2>' as 'value2'
    And I add 'value1' to 'value2' and store it as 'value3'
    Then 'value3' should be equal to '<result>'

    Examples:
      | value1 | value2 | result |
      | 2      | 3      | 5      |
      | 3      | 4      | 7      |

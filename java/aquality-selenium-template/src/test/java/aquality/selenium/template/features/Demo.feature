Feature: Demo

  @demo
  Scenario: Empty email validation is working on Contact Us page
    Given Main page is opened
    When I open Contact us page
    Then Contact us page is opened
    When I fill contact form using following data:
      | Name     | Peter Parker             |
      | Company  | Aquality Automation      |
      | Phone    | +44 208 816 7320         |
      | Comment  | I'd like to contact you! |
    And I accept Privacy and Cookies Policy
    And I click Send button
    Then Notification about empty fields is present

  @demo
  Scenario: Empty email validation is NOT working on Contact Us page
    Given Main page is opened
    When I open Contact us page
    Then Contact us page is opened
    When I fill contact form using following data:
      | Name     | Peter Parker             |
      | Company  | Aquality Automation      |
      | Phone    | +44 208 816 7320         |
      | Comment  | I'd like to contact you! |
    And I accept Privacy and Cookies Policy
    And I click Send button
    Then Notification about empty fields isn't present
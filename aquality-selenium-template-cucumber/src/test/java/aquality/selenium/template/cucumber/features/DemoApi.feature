@API @demo
Feature: Demo API

  Scenario: GET user info
    When I send GET '/users/aqualityAutomation' request to github with saving the 'response'
    Then the status code of the 'response' is '200'
      And the 'response' matches json schema 'users/UserResponse'
      And the 'login' is 'aqualityAutomation' in the 'response'
      And the 'type' is 'User' in the 'response'
      And the 'response' time is less than or equal to 5 seconds
    When I save the user from the 'response' as 'user1'
      And I send GET '/users/aquality-automation' request to github with saving the 'response2'
      And I save the user from the 'response2' as 'user2'
    Then User 'user1' is different from the user 'user2'

  Scenario: GET organization info
    When I send GET '/users/aquality-automation' request to github with saving the 'response'
    Then the status code of the 'response' is '200'
      And the 'response' matches json schema 'users/UserResponse'
      And the 'login' is 'aquality-automation' in the 'response'
      And the 'type' is 'Organization' in the 'response'
      And the 'id' is 50261201 in the 'response'
      And the 'response' time is less than or equal to 5 seconds

  Scenario: GET users info
    When I send GET '/users' request to github with saving the 'users response'
    Then the status code of the 'users response' is '200'
      And the 'users response' matches json schema 'users/UsersResponse'
      And the 'users response' time is less than or equal to 5 seconds
      And the '.' array has size less than or equal to 30 in the 'users response'
      And the '.' array is ordered ascending by 'id' in the 'users response'
    When I extract the '[0].url' from the 'users response' with saving it as 'user URL'
      And I send GET request to github endpoint saved as 'user URL' with saving the 'response'
    Then the status code of the 'response' is '200'
      And the 'response' matches json schema 'users/UserResponse'
      And the 'url' has the value saved as 'user URL' in the 'response'
      And the 'response' time is less than or equal to 5 seconds

  Scenario: GET user info clone
    When I send GET '/users/aqualityAutomation' request to github with saving the 'response' clone
    Then the status code of the 'response' is '200' clone
      And the 'response' matches json schema 'users/UserResponse' clone
      And the 'login' is 'aqualityAutomation' in the 'response' clone
      And the 'type' is 'User' in the 'response' clone
      And the 'response' time is less than or equal to 5 seconds clone
    When I save the user from the 'response' as 'user1' clone
      And I send GET '/users/aquality-automation' request to github with saving the 'response2' clone
      And I save the user from the 'response2' as 'user2' clone
    Then User 'user1' is different from the user 'user2' clone

  Scenario: GET organization info clone
    When I send GET '/users/aquality-automation' request to github with saving the 'response' clone
    Then the status code of the 'response' is '200' clone
      And the 'response' matches json schema 'users/UserResponse' clone
      And the 'login' is 'aquality-automation' in the 'response' clone
      And the 'type' is 'Organization' in the 'response' clone
      And the 'id' is 50261201 in the 'response' clone
      And the 'response' time is less than or equal to 5 seconds clone

  Scenario: GET users info clone
    When I send GET '/users' request to github with saving the 'users response' clone
    Then the status code of the 'users response' is '200' clone
      And the 'users response' matches json schema 'users/UsersResponse' clone
      And the 'users response' time is less than or equal to 5 seconds clone
      And the '.' array has size less than or equal to 30 in the 'users response' clone
      And the '.' array is ordered ascending by 'id' in the 'users response' clone
    When I extract the '[0].url' from the 'users response' with saving it as 'user URL' clone
      And I send GET request to github endpoint saved as 'user URL' with saving the 'response' clone
    Then the status code of the 'response' is '200' clone
      And the 'response' matches json schema 'users/UserResponse' clone
      And the 'url' has the value saved as 'user URL' in the 'response' clone
      And the 'response' time is less than or equal to 5 seconds clone

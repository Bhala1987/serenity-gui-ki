@login
Feature: Login
  As a registered user
  I want to test the login functionality
  Such that I can login successfully

  # POSITIVE SUCCESSFUL FLOW ALONG WITH COOKIES & CACHE TESTING
  @smoke
  Scenario: Successful login
    Given launch the url "https://www.hudl.com/login"
    And enter the email
    And enter the password matching the email
    When click on "Log In" button
    Then should be logged in successfully
    And set the cookies
    Given get the cookies
    Then should be logged in successfully
    Given clear the cache
    And get the cookies
    Then should be logged in successfully

    # NEGATIVE TESTS
  Scenario Outline: Login failures
    Given launch the url "https://www.hudl.com/login"
    And enter the email "<email>"
    And enter the password "<password>"
    When click on "Log In" button
    Then the error message "We didn't recognize that email and/or password.Need help?" should be displayed
    Examples:
      | email | password |
      | email | password |
      |       | password |
      | email |          |

    # OTHER ROUTES FOR LOGIN
  Scenario: Other routes for login
    Given launch the url "https://www.hudl.com/"
    And click on "Accept All Cookies" button
    And click on "Log in" link
    And click on "Hudl Log in" link
    And enter the email
    And enter the password matching the email
    When click on "Log In" button
    Then should be logged in successfully

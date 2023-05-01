@logout
Feature: Logout
  As a signed/logged in customer
  I want to log off successfully

  # POSITIVE LOGOUT
  @smoke
  Scenario: Successful logout
    Given launch the url "https://www.hudl.com/login"
    And enter the email
    And enter the password matching the email
    When click on "Log In" button
    Then should be logged in successfully
    When click on "Log Out" link
    Then should be logged out successfully

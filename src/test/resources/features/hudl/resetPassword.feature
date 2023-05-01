@resetPassword
Feature: Reset Password
  As a forgotten password customer/client
  I want to reset the password successfully

  # POSITIVE RESET PASSWORD FLOW
  @smoke
  Scenario: Reset Password
    Given launch the url "https://www.hudl.com/login"
    And click on "Need help?" link
    When enter the email "someemail@email.com" in the reset password screen
    And click on "Send" button
    Then check the check your email message

    # NEGATIVE TESTS
  Scenario Outline: Forgot password - failure scenarios
    Given launch the url "https://www.hudl.com/login"
    And click on "Need help?" link
    When enter the email "<invalid_email>" in the reset password screen
    And click on "Send" button
    Then the error message "That isn't a valid email address. Make sure to use the email@domain.com format." should be displayed on the reset password screen
    Examples:
      | invalid_email |
      | invalid_email |
      | 1234          |
      | £%&^(         |
      | ab67fd$%#     |
      |               |




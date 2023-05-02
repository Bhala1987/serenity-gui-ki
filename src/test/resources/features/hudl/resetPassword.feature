@resetPassword
Feature: Reset Password
  As a forgotten password customer/client
  I want to reset the password successfully

  # POSITIVE RESET PASSWORD FLOW (PROVIDING SOME NON-EXISTENT EMAIL ADDRESS SINCE IT'S PRODUCTION WEBSITE)
  @smoke
  Scenario: Reset Password
    Given launch the base url
    And click on "Need help?" link
    When enter the email "jj@jj.com" in the reset password screen
    And click on "Send" button
    Then check the check your email message

    # NEGATIVE TESTS
  Scenario Outline: Reset password - failure scenarios
    Given launch the base url
    And click on "Need help?" link
    When enter the email "<invalid_email>" in the reset password screen
    And click on "Send" button
    Then the error message "<error_message>" should be displayed on the reset password screen
    Examples:
      | invalid_email       | error_message                                                                                                        |
      | invalid_email       | That isn't a valid email address. Make sure to use the email@domain.com format.                                      |
      | 1234                | That isn't a valid email address. Make sure to use the email@domain.com format.                                      |
      | £%&^(               | That isn't a valid email address. Make sure to use the email@domain.com format.                                      |
      | ab67fd$%#           | That isn't a valid email address. Make sure to use the email@domain.com format.                                      |
      |                     | That isn't a valid email address. Make sure to use the email@domain.com format.                                      |
      | someemail@email.com | That email address doesn't exist in Hudl. Check with your coach to ensure they have the right email address for you. |

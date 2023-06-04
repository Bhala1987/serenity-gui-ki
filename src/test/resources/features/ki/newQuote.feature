@newQuote
Feature: Creation of new quote
  As an insurance broker
  I want to create a new quote
  Such that I can provide a quote to my clients

  Background:
    Given launch the base url

  # POSITIVE SUCCESSFUL FLOW OF CREATING A NEW QUOTE
  @smoke
  Scenario Outline: Successful creation of new quote
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country   | Primary Insured   | Class of Business   |
      | <Primary Country> | <Primary Insured> | <Class of Business> |
    And click on "Next" button
    And the inception date is <month> month from now
    And enter the AUM value as "<AUM>"
    And enter the Premium value as "<Premium>"
    When click on "Next" button
    Then verify the successful creation of quote
    Examples:
      | Primary Country | Primary Insured   | Class of Business | month | AUM    | Premium |
      | Japan           | Finance Group Ltd | Energy            | 1     | 150000 | 5620.81 |

    # NEGATIVE / ERROR SCENARIOS
  Scenario: First page - no input
    When click on "Next" button
    Then the error message "This is a required question" should be displayed against the field

  Scenario: Insured Info page - no input
    And click on "Create a new quote" radio button
    And click on "Next" button
    When click on "Next" button
    Then the error message "This is a required question" should be displayed against the field
    And enter the following details
      | Primary Country | Primary Insured | Class of Business |
      | France          |                 |                   |
    When click on "Next" button
    Then the error message "This is a required question" should be displayed against the field

  Scenario: Layer 1 page - no input
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country | Primary Insured   | Class of Business |
      | Japan           | Finance Group Ltd | Energy            |
    And click on "Next" button
    When click on "Next" button
    Then the error message "This is a required question" should be displayed against the field
    And the inception date is 1 month from now
    When click on "Next" button
    Then the error message "This is a required question" should be displayed against the field

  Scenario: Layer 1 page - invalid input
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country | Primary Insured   | Class of Business |
      | Japan           | Finance Group Ltd | Energy            |
    And click on "Next" button
    When enter the AUM value as "invalid"
    Then the error message "Specify amount in US dollar" should be displayed against the field

  Scenario: Layer 1 page - Invalid date
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country | Primary Insured   | Class of Business |
      | Japan           | Finance Group Ltd | Energy            |
    And click on "Next" button
    And the inception date is 5000 month from now
    Then the error message "Invalid date" should be displayed against the field
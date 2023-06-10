@clearForm @back
Feature: Clear form & Back buttons
  As an insurance broker
  I want to clear the filled in details when clicked on Clear form button
  Such that I can again fill in with different values
  Also I want to navigate back using the Back button
  Such that I can check the values entered

  Background:
    Given launch the base url

  Scenario: Clear form on first page
    And click on "See my pending quotes" radio button
    And click on "Clear form" button
    And the clear form dialog box should appear
    And click on "Cancel" button
    And click on "Clear form" button
    And the clear form dialog box should appear
    When click on "Clear form on dialog" button
    Then the filled in details should be cleared

  Scenario: Clear form on new quote insured info page
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country | Primary Insured   | Class of Business |
      | Spain           | Finance Group Ltd | Cyber             |
    And click on "Clear form" button
    And the clear form dialog box should appear
    And click on "Cancel" button
    And click on "Clear form" button
    And the clear form dialog box should appear
    When click on "Clear form on dialog" button
    Then the filled in details should be cleared

  Scenario: Clear form on new quote layer page
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country | Primary Insured  | Class of Business |
      | France          | Service Tech Ltd | Energy            |
    And click on "Next" button
    And the inception date is 1 month from now
    And enter the AUM value as "10000"
    And enter the Premium value as "50"
    And click on "Clear form" button
    And the clear form dialog box should appear
    When click on "Clear form on dialog" button
    Then the filled in details should be cleared

  Scenario: Back button functionality check
    And click on "Create a new quote" radio button
    And click on "Next" button
    And enter the following details
      | Primary Country | Primary Insured  | Class of Business |
      | Japan           | Service Tech Ltd | Cyber             |
    And click on "Back" button
    And click on "Next" button
    And click on "Next" button
    And the inception date is 1 month from now
    And enter the AUM value as "10000"
    And enter the Premium value as "50"
    And click on "Back" button
    And click on "Back" button
    And click on "Next" button
    And click on "Next" button
    When click on "Next" button
    Then verify the successful creation of quote

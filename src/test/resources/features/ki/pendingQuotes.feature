@pendingQuotes
Feature: View the pending quotes
  As an insurance broker
  I want to see any pending quotes
  Such that I can provide a quote to my clients

  # POSITIVE SUCCESSFUL FLOW OF VIEWING THE PENDING QUOTES
  @smoke
  Scenario: Successful viewing of pending quotes
    Given launch the base url
    And click on "See my pending quotes" radio button
    When click on "Next" button
    Then I should see the insureds "Service Tech Ltd,Logistics Worldwide Inc,XYZ Holdings PLC" in the pending quotes
    And I should see the classes & premiums "CLASS: Cyber // PREMIUM: $560K,CLASS: Property // PREMIUM: $2.4M,CLASS: Energy // PREMIUM: $1.3M" in the pending quotes

@signup
Feature: Signup
  As a customer/client
  I want to signup/register successfully

  #NOT FULLY SIGNING UP AS IT'S A PRODUCTION WEBSITE
  @smoke
  Scenario: Successful signup without submitting the form
    Given launch the url "https://www.hudl.com/login"
    And click on "Sign up" link
    And click on "High Schools, Clubs & Colleges" link
    And click on "Accept All Cookies" button
    And enter the details
      | First name | Last name | Email address        | Phone number | Country       | State      | Org name | Org level                 | Org type    | Sport             | Role       | Message            |
      | James      | Smith     | James.Smith@hudl.com | 07876800188  | United States | Washington | Hudl     | High School/Middle School | High School | Swimming & Diving | Head Coach | Signing up to Hudl |
#    And click on "Send" button

  # NEGATIVE TESTS
  Scenario: Signup - failure scenarios
    Given launch the url "https://www.hudl.com/login"
    And click on "Sign up" link
    And click on "High Schools, Clubs & Colleges" link
    And click on "Accept All Cookies" button
    When enter the details
      | First name | Last name | Email address        | Phone number | Country       | State      | Org name | Org level                 | Org type    | Sport             | Role       | Message            |
      |            | Smith     | James.Smith@hudl.com | 07876800188  | United States | Washington | Hudl     | High School/Middle School | High School | Swimming & Diving | Head Coach | Signing up to Hudl |
    And click on "Send" button
    Then check the error message "This field is required." on the field "First name"
    When enter the details
      | First name | Last name | Email address | Phone number | Country       | State      | Org name | Org level                 | Org type    | Sport             | Role       | Message            |
      | James      | Smith     | 1234          | 07876800188  | United States | Washington | Hudl     | High School/Middle School | High School | Swimming & Diving | Head Coach | Signing up to Hudl |
    And click on "Send" button
    Then check the error message "Please enter a valid email." on the field "Email address"
    When enter the details
      | First name | Last name | Email address        | Phone number | Country       | State      | Org name | Org level                 | Org type    | Sport             | Role       | Message            |
      | James      | Smith     | James.Smith@hudl.com | abcd         | United States | Washington | Hudl     | High School/Middle School | High School | Swimming & Diving | Head Coach | Signing up to Hudl |
    And click on "Send" button
    Then check the error message "Valid characters are numbers, (), -, +, and spaces." on the field "Phone"
    When enter the details
      | First name | Last name | Email address        | Phone number | Country       | State      | Org name | Org level                 | Org type | Sport             | Role       | Message            |
      | James      | Smith     | James.Smith@hudl.com | 07876800188  | United States | Washington | Hudl     | High School/Middle School |          | Swimming & Diving | Head Coach | Signing up to Hudl |
    And click on "Send" button
    Then check the error message "This field is required." on the field "Org type"


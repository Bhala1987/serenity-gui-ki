# Introduction

This is a Serenity - Selenium framework project for functional testing automation of GUI (Graphical User Interface) /
web uis/pages/applications.

## Pre-requisites / Installation:

1. Java (here used JDK17) programming language - **_needs installation_**
2. Maven (here used 3.9.1) software project management tool - **_needs installation_**
3. Git (here used 2.40.0) version control system - **_needs installation_**
4. Serenity (here used 3.7.0) framework with in-built/embedded selenium framework
5. Other libraries as specified in maven pom.xml file
6. Google Chrome browser
7. Mozilla Firefox browser
8. Any other required browsers like Microsoft Internet Explorer, Microsoft Edge, Apple Safari, etc...

### Assumptions:

1. Browsers - Google Chrome & Mozilla Firefox
2. Mobile Responsive - width & height dimensions of devices: Tablets: {'ipadair','surfacepro7','nesthubmax'} and
   Mobile Phones: {'iphonese','iphone12pro','samsunggalaxys20ultra'}

### TESTS:

#### New Quotes Creation:

1. Positive successful creation of a new quote & checking the success message
2. Negative cases by not selecting the required fields
3. Negative cases by entering invalid inputs on numerical fields
4. Negative cases by entering the invalid dates

#### View Pending Quotes:

1. Positive successful validation & verification of pending quotes list checks

#### Clear form:

1. Clicking on clear form button clears out the filled in details

#### Back:

1. Clicking on Back button to navigate back to the previous page

##### HOW TO RUN THE TESTS?

Example:  mvn clean verify -Dwebdriver.driver=chrome -Dheadless.mode=false -Dfailsafe.rerunFailingTestsCount=2
-Dcucumber.filter.tags=@pendingQuotes

1. webdriver.driver can be of chrome, firefox, edge, safari
2. headless.mode should be boolean either true or false to run the tests virtually/headless or not
3. failsafe.rerunFailingTestsCount is of integer & should be greater than zero to rerun the failed tests
4. tablet.emulator can be of 'ipadair','surfacepro7','nesthubmax'
   ```mvn clean verify -Dtablet.emulator=ipadair -Dfailsafe.rerunFailingTestsCount=2```
5. mobile.emulator can be of 'iphonese','iphone12pro','samsunggalaxys20ultra'
   ```mvn clean verify -Dmobile.emulator=samsunggalaxys20ultra -Dfailsafe.rerunFailingTestsCount=1 -Dcucumber.filter.tags=@newQuote```
6. cucumber.filter.tags is to filter out the tags and only those scenarios having tags
7. To run all the tests:
   ```mvn clean verify -Dwebdriver.driver=firefox -Dheadless.mode=false -Dfailsafe.rerunFailingTestsCount=2```
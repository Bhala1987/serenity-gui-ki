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
3. Mobile App - APK was not provided hence ignoring the app testing using Appium

### TESTS:

#### SIGNUP:

1. Positive successful sign up / registration but not submitting as it's production website
2. Negative cases by entering the invalid inputs onto the registration fields

#### LOGIN:

1. Positive successful login with valid email address and password
2. Negative cases by entering the invalid credentials
3. Checking that the password is masked in the front-end
4. Other login routes via website
5. Cookies
6. Cache

#### LOGOUT:

1. Successful logout

#### RESET/FORGOTTEN PASSWORD:

1. Reset Password try only as it's a production website
2. Negative cases by entering the invalid inputs onto the email address field

##### HOW TO RUN THE TESTS?

Example:  mvn clean verify -Dwebdriver.driver=chrome -Dheadless.mode=false -Dfailsafe.rerunFailingTestsCount=2
-Dcucumber.filter.tags=@logout

1. webdriver.driver can be of chrome, firefox, edge, safari
2. headless.mode should be boolean either true or false to run the tests virtually/headless or not
3. failsafe.rerunFailingTestsCount is of integer & should be greater than zero to rerun the failed tests
4. tablet.emulator can be of 'ipadair','surfacepro7','nesthubmax'
   ```mvn clean verify -Dtablet.emulator=ipadair -Dfailsafe.rerunFailingTestsCount=2```
5. mobile.emulator can be of 'iphonese','iphone12pro','samsunggalaxys20ultra'
   ```mvn clean verify -Dmobile.emulator=samsunggalaxys20ultra -Dfailsafe.rerunFailingTestsCount=1 -Dcucumber.filter.tags=@login```
6. cucumber.filter.tags is to filter out the tags and only those scenarios having tags

#### Notes:

1. Decided to mask/hide the credentials using my own encoding/decoding technique, here I've used it as mentioned in the
   utilities.DecryptHelper class method.
2. Written the Base58 class since bitcoinj maven library is not compatible with the serenity/selenium framework, hence
   having here in this project as a separate class. Raised an
   issue: https://github.com/serenity-bdd/serenity-core/issues/3125 as a reference.
3. Cookies testing is another challenging test due to nature of cookies storage when launching URL+/home only

###### Defects:

1. deselect() on dropdown options is not working, hence need to refresh the page every time to clear the data on the
   inputs fields in signup page.
2. Skip to main content banner on hudl.com is not disappearing when clicked on it, hence need to refresh the page to get
   away with it.
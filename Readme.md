#  Test task for Praktika.ai
by Konstantin Kochetov

## Task description
Please download our app from Google Play on your mobile device or emulated device.
We request you to cover the following test case using automated tests:
1.	Click “Get started.”
2.	Go through onboarding to the language selection screen.
3.	Change the language.
Please feel free to include assertions and drivers for either an emulator or a real device. Utilise universal locators for elements wherever possible.

## My solution
As mentioned in the description I used
- Appium server itself.
- Java client for Appium.
- UiAutomator2 android driver.
- TestNG as a testing framework (It is only because I used it on my last Java project. I have no problem with JUnit).
- Allure for report making (It is a convenient tool for reporting).
- Maven for build and run tests.
- And my Samsung as a testing device.
- Appium Inspector app to find elements and test locators.

There is an auto-test for the described scenario in OnboardingProcessTest.java file with some descriptions.

There is a common process for autotests:
- Initialize environment (appium driver, install the app if needed, clean data, etc.)
- Run test scenarios with actions and assertions.
- Make a report.
- Uninitialize the environment.

I used a Page Object Model for making a model of application under the test. It is in com.praktika.pages package.
I tried to encapsulate all logic into this page's classes. It is a good practice because it makes tests "self-documented".

As for the, I don`t have access to .apk, the application should be installed on the device before running tests
These tests need some Environment variables to run:
- PLATFORM -- default "android".
- deviceName -- device name for running tests
- Package -- package to run and test 
- appiumURL -- URL or Appium server (local in my case)

All these vars for now have default values, defined in DriverFactory.java class. It can be redefined by cd\ci or manually set.

To run tests:
```bash
mvn install
mvn -DPLATFORM=android -DdeviceName=samsungPhone DdeviceName=samsungPhone -DPackage=ai.praktika.android -DappiumURL=http://127.0.0.1:4723 test
```

Or just 
```bash
mvn install
mvn test
```
to use default values.

After tests are passed (or failed) in folder `target/allure-results` appears files with results. It can be uploaded to Allure TestOps service or be used to make a local html report.
Allure cli should be installed.
```bash
cd target
allure generate
```

After that `target/allure-report` contains a HTML report.

I made and placed in `reportExample` folder two reports: One failed test with a screenshot. Another with all green passed tests.

In order not to prolong the task completion time I put aside some topics:
- Running the same code on the iOS platform
- Handling different locators for different platforms
- Managing localization (to take all strings from the same source as for the app itself)
- Run tests in parallel

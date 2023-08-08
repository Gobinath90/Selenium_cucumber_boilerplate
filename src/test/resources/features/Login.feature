@E2E
Feature: Login Test  Etana

  Background: Navigate to Login page
    Given User navigate to Login Page

  @BVT
  Scenario: TC_1: Verify Login to the Etana Application with individual profile
    And user enters username "qa.testing2203+22011301@gmail.com" and password "qa.testing2203+22011301@gmail.com" of individual profile
    And user clicks on login button
    Then user is logged in successully
    And There should be no broken links
    Then user logouts from the application

  @smoke
  Scenario: TC_2: Verify Login to the Etana Application with Organization profile
    And user enters username "qa.testing2203+22011301@gmail.com" and password "qa.testing2203+22011301@gmail.com" of organization profile
    And user clicks on login button
    Then user is logged in successully
    And There should be no broken links
    Then user logouts from the application

  @smoke
  Scenario: TC_3: Verify if the data in password field is either visible as asterisk or bullet signs
    And user enters username "qa.testing2203+22011301@gmail.com" and password "qa.testing2203+22011301@gmail.com"
    Then Password field is visible as asterisk

  @smoke
  Scenario: TC_4: Verify Login to the Etana Application with invalid credentials
    And user enters invalid username "qa.testing2203+22011302@gmail.com" and password "qa.testing2203+22011301@gmail.com"
    And user clicks on login button
    Then user should see an error message

  @sanity
  Scenario: TC_5: Verify Login to the Etana Application with empty credentials
    And user clicks on login button
    Then user should see an error message

  @smoke
  Scenario: TC_6: Verify Registration for Individual profile
    And user clicks on register button
    When user enters registration details

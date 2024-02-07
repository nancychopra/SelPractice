
Feature: Error Validation
  Validating login error

  @Regression
  Scenario Outline: Login test
    Given I landed on Ecommerce page
    When Logged in with username "<username>" and password "<password>"
    Then "Incorrect email or password." login message displayed
    Examples:
      | username |password|
      |nancy.chopra@gmail.com|Selenium@123456|
      |nancy.chopra@gmail.com|Selenium@123457|
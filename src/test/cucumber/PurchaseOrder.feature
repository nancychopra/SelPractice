
Feature: Purchase the order from ecommerce
  I want to purchase an item from web application

  Background:
    Given I landed on Ecommerce page

  Scenario Outline: Positive test to purchase an order
    Given Logged in with username "<username>" and password "<password>"
    When  I add product "<product>" to cart
    And Checkout "<product>" and submit order
    Then "THANKYOU FOR THE ORDER." message
    Examples:
      | username |password|product|
      |nancy.chopra@gmail.com|Selenium@12345|ADIDAS ORIGINAL|
      |nancy.chopra@gmail.com|Selenium@12345|ZARA COAT 3|
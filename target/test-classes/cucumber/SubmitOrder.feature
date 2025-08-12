
@tag
Feature: Purchase the order from Ecommerce Website I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
    
    @Regression
    Scenario Outline: Positive Test of Sumitting the order
    
    Given Log in with username <name> and password <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
    Examples:
     | name				  | password | productName |
     | ecomuser@gmail.com | Ecom@123 | ZARA COAT 3 |
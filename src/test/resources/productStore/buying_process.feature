@buying-process
Feature: buying process
  As a customer of the Product Store,
  I want to view and browse through the available products,
  select items of interest, add them to my cart,
  proceed to checkout, and complete the purchase transaction.

  Scenario: buying process
    Given I am on Product Store Home Page and can view list of products
    When I select a product to view details
    And I add product to cart
    When I find my selected products in cart
    Then I check out and buy the product
    And I get order placed successfully message





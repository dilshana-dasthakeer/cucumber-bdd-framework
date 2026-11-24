Feature: Checkout
  As a shopper I want to place an order.

  Background:
    Given the shopper is signed in as "standard_user"
    And the shopper adds "Sauce Labs Onesie" to the cart
    And the shopper opens the cart

  @smoke @regression
  Scenario: A shopper completes an order
    When the shopper starts checkout
    And the shopper enters the delivery details "Dilshana", "Dasthakeer" and "20000"
    And the shopper places the order
    Then the order confirmation says "Thank you for your order!"

  @regression
  Scenario: Checkout needs a postal code
    When the shopper starts checkout
    And the shopper enters the delivery details "Dilshana", "Dasthakeer" and ""
    Then checkout is blocked with the message "Postal Code is required"

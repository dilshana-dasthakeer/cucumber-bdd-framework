Feature: Browse products
  As a shopper I want to sort products and fill a cart.

  Background:
    Given the shopper is signed in as "standard_user"

  @smoke @regression
  Scenario: Products added to the cart appear in the cart
    When the shopper adds "Sauce Labs Backpack" to the cart
    And the shopper adds "Sauce Labs Bike Light" to the cart
    Then the cart badge shows 2
    When the shopper opens the cart
    Then the cart contains:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |

  @regression
  Scenario: Products can be sorted by name
    When the shopper sorts the products by "Name (A to Z)"
    Then the products are listed from A to Z

  @regression
  Scenario: Products can be sorted by price
    When the shopper sorts the products by "Price (low to high)"
    Then the products are listed from the lowest price to the highest

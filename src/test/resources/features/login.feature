Feature: Sign in
  As a shopper I want to sign in so that I can buy products.

  @smoke @regression
  Scenario: A valid shopper reaches the product catalogue
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "secret_sauce"
    Then the product catalogue is shown

  @regression
  Scenario Outline: Sign in is refused for invalid details
    Given the shopper is on the login page
    When the shopper signs in with username "<username>" and password "<password>"
    Then the login error says "<message>"

    Examples:
      | username        | password     | message                                        |
      | locked_out_user | secret_sauce | this user has been locked out                  |
      | standard_user   | wrong        | Username and password do not match             |
      | standard_user   |              | Password is required                           |
      |                 | secret_sauce | Username is required                           |

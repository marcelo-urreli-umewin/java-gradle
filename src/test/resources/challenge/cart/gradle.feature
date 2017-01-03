Feature: Shopping Cart Sum

  Scenario: Shopping Cart Sum updated
    Given Two products added to the shopping cart.
    When User goes to bag an adds one more instance of a particular product.
    Then the total sum is calculated correctly.

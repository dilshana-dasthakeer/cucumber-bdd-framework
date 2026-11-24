package com.dilshana.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.dilshana.bdd.pages.CartPage;
import com.dilshana.bdd.pages.CheckoutPage;
import com.dilshana.bdd.support.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private final TestContext context;

    public CheckoutSteps(TestContext context) {
        this.context = context;
    }

    @When("the shopper starts checkout")
    public void theShopperStartsCheckout() {
        new CartPage(context.driver()).checkout();
    }

    @When("the shopper enters the delivery details {string}, {string} and {string}")
    public void theShopperEntersTheDeliveryDetails(String firstName, String lastName, String postalCode) {
        new CheckoutPage(context.driver()).enterDetails(firstName, lastName, postalCode);
    }

    @When("the shopper places the order")
    public void theShopperPlacesTheOrder() {
        new CheckoutPage(context.driver()).finish();
    }

    @Then("the order confirmation says {string}")
    public void theOrderConfirmationSays(String message) {
        assertThat(new CheckoutPage(context.driver()).confirmation()).isEqualTo(message);
    }

    @Then("checkout is blocked with the message {string}")
    public void checkoutIsBlockedWithTheMessage(String message) {
        assertThat(new CheckoutPage(context.driver()).errorMessage()).contains(message);
    }
}

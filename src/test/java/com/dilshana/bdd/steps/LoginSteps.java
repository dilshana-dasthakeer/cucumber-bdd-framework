package com.dilshana.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.dilshana.bdd.pages.InventoryPage;
import com.dilshana.bdd.pages.LoginPage;
import com.dilshana.bdd.support.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("the shopper is on the login page")
    public void theShopperIsOnTheLoginPage() {
        new LoginPage(context.driver()).open();
    }

    @Given("the shopper is signed in as {string}")
    public void theShopperIsSignedInAs(String username) {
        new LoginPage(context.driver()).open().loginAs(username, "secret_sauce");
    }

    @When("the shopper signs in with username {string} and password {string}")
    public void theShopperSignsIn(String username, String password) {
        new LoginPage(context.driver()).loginAs(username, password);
    }

    @Then("the product catalogue is shown")
    public void theProductCatalogueIsShown() {
        assertThat(new InventoryPage(context.driver()).title()).isEqualTo("Products");
    }

    @Then("the login error says {string}")
    public void theLoginErrorSays(String message) {
        assertThat(new LoginPage(context.driver()).errorMessage()).contains(message);
    }
}

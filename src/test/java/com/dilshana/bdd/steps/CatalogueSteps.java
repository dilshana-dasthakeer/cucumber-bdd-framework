package com.dilshana.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.dilshana.bdd.pages.CartPage;
import com.dilshana.bdd.pages.InventoryPage;
import com.dilshana.bdd.support.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatalogueSteps {

    private final TestContext context;

    public CatalogueSteps(TestContext context) {
        this.context = context;
    }

    @When("the shopper adds {string} to the cart")
    public void theShopperAddsToTheCart(String product) {
        new InventoryPage(context.driver()).addToCart(product);
    }

    @When("the shopper sorts the products by {string}")
    public void theShopperSortsTheProductsBy(String option) {
        new InventoryPage(context.driver()).sortBy(option);
    }

    @When("the shopper opens the cart")
    public void theShopperOpensTheCart() {
        new InventoryPage(context.driver()).openCart();
    }

    @Then("the cart badge shows {int}")
    public void theCartBadgeShows(int count) {
        assertThat(new InventoryPage(context.driver()).cartCount()).isEqualTo(count);
    }

    @Then("the cart contains:")
    public void theCartContains(DataTable table) {
        List<String> expected = table.asList();
        assertThat(new CartPage(context.driver()).itemNames()).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Then("the products are listed from A to Z")
    public void theProductsAreListedFromAToZ() {
        List<String> names = new InventoryPage(context.driver()).productNames();
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        assertThat(names).isEqualTo(sorted);
    }

    @Then("the products are listed from the lowest price to the highest")
    public void theProductsAreListedByPrice() {
        List<Double> prices = new InventoryPage(context.driver()).productPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        assertThat(prices).isEqualTo(sorted);
    }
}

package com.dilshana.bdd.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test='title']");
    private static final By ITEM_NAMES = By.cssSelector("[data-test='inventory-item-name']");
    private static final By ITEM_PRICES = By.cssSelector("[data-test='inventory-item-price']");
    private static final By SORT = By.cssSelector("[data-test='product-sort-container']");
    private static final By CART_LINK = By.cssSelector("[data-test='shopping-cart-link']");
    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String title() {
        return textOf(TITLE);
    }

    /** Sauce Demo builds each button id from the product name, for example add-to-cart-sauce-labs-backpack. */
    public void addToCart(String productName) {
        click(By.cssSelector("[data-test='add-to-cart-" + slug(productName) + "']"));
    }

    public int cartCount() {
        List<WebElement> badge = driver.findElements(CART_BADGE);
        return badge.isEmpty() ? 0 : Integer.parseInt(badge.get(0).getText());
    }

    public void sortBy(String visibleText) {
        visible(SORT);
        new Select(driver.findElement(SORT)).selectByVisibleText(visibleText);
    }

    public List<String> productNames() {
        visible(ITEM_NAMES);
        return driver.findElements(ITEM_NAMES).stream().map(WebElement::getText).toList();
    }

    public List<Double> productPrices() {
        visible(ITEM_PRICES);
        return driver.findElements(ITEM_PRICES).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .toList();
    }

    public void openCart() {
        click(CART_LINK);
    }

    private static String slug(String name) {
        return name.toLowerCase().replace(" ", "-");
    }
}

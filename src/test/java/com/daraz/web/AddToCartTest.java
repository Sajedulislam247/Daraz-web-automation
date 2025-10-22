package com.daraz.web;

import com.daraz.web.page.SearchResultPage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class AddToCartTest extends BaseTest {
    public static final String websiteUrl = ConfigReader.getProperty("websiteUrl");
    public static final String userEmail = ConfigReader.getProperty("userEmail");
    public static final String userPassword = ConfigReader.getProperty("userPassword");
    public static final String productNameFirst = ConfigReader.getProperty("productNameFirst");
    public static final String productNameSecond = ConfigReader.getProperty("productNameSecond");

    @Test(description = "Verify Product successfully added to Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addItemToCartShouldSucceed() {

        LoginTest
                .loginWithCredentials(page, websiteUrl, userEmail, userPassword);
        SearchTest
                .searchProduct(page, productNameFirst);

        SearchResultPage searchResultPage = new SearchResultPage(page);

        searchResultPage
                .selectRandomProduct();

        String selectedProduct = searchResultPage.getProductTitle().textContent();

        searchResultPage
                .clickAddToCartButton();

        assertThat(searchResultPage.getCartMessageLocator()).containsText("Added to cart successfully!");

        searchResultPage
                .closeModalWindow()
                .clickCartIcon();

        assertThat(searchResultPage.getCartItemListLocator().getByText(selectedProduct)).isVisible();
    }


    @Test(description = "Verify Second Product successfully added to Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addSecondItemToCartShouldSucceed() {

        LoginTest
                .loginWithCredentials(page, websiteUrl, userEmail, userPassword);
        SearchTest
                .searchProduct(page, productNameSecond);
        String selectedProduct = selectProductAndAddToCart(page);

        SearchResultPage searchResultPage = new SearchResultPage(page);
        assertThat(searchResultPage.getCartMessageLocator()).containsText("Added to cart successfully!");

        searchResultPage
                .closeModalWindow()
                .clickCartIcon();

        assertThat(searchResultPage.getCartItemListLocator().getByText(selectedProduct)).isVisible();
    }

    static String selectProductAndAddToCart(Page page) {
        SearchResultPage searchResultPage = new SearchResultPage(page);
        searchResultPage.selectRandomProduct();
        String selectedProduct = searchResultPage.getProductTitle().textContent();
        searchResultPage.clickAddToCartButton();
        return selectedProduct;
    }
}

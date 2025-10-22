package com.daraz.web;

import com.daraz.web.page.SearchPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class SearchTest extends BaseTest {
    String websiteUrl = ConfigReader.getProperty("websiteUrl");
    String userEmail = ConfigReader.getProperty("userEmail");
    String userPassword = ConfigReader.getProperty("userPassword");
    String productToSearchFor = ConfigReader.getProperty("productNameFirst");

    @Test(description = "Verify Product Search Functional")
    @Severity(SeverityLevel.CRITICAL)
    public void searchWithProductNameShouldSucceed() {
        LoginTest.loginWithCredentials(page, websiteUrl, userEmail, userPassword);
        searchProduct(page, productToSearchFor);

        SearchPage searchPage = new SearchPage(page);
        assertThat(searchPage.getSearchInputFieldLocator()).hasValue(productToSearchFor);
        assertThat(searchPage.getSearchResultHeaderLocator()).containsText(productToSearchFor);
        assertThat(searchPage.getSearchResultCountLocator()).containsText(productToSearchFor);
    }

    @Description("Search for product")
    static void searchProduct(Page page, String productToSearchFor) {
        SearchPage searchPage = new SearchPage(page);

        searchPage.fillProductName(productToSearchFor)
                .clickSearchButton();
        page.waitForLoadState(LoadState.LOAD);
    }

}

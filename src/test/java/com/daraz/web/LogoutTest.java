package com.daraz.web;

import com.daraz.web.page.HomePage;
import com.daraz.web.page.LoginPage;
import com.daraz.web.page.SearchPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Epic("User Management")
@Feature("Login Functionality")
public class LogoutTest extends BaseTest {
    private final String websiteUrl = ConfigReader.getProperty("websiteUrl");
    private final String userEmail = ConfigReader.getProperty("userEmail");
    private final String userPassword = ConfigReader.getProperty("userPassword");

    @Test(description = "Verify successful logout from user account")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Logout Flow")
    @Description("Ensures user can login and logout from their account")
    public void logoutFromUserAccountShouldSucceed() {

        loginWithCredentials(page, websiteUrl, userEmail, userPassword);
        LoginPage loginPage = new LoginPage(page);
        assertThat(loginPage.getMyAccountNevLocator()).containsText("account");
        assertFalse(loginPage.getLoginButtonLocator().isVisible());

        logoutFromUserAccount(page);
        assertTrue(loginPage.getLoginButtonLocator().isVisible());
        assertTrue(loginPage.getMyAccountNevLocator().innerText().isEmpty());
    }

    @Description("Login with Credentials")
    public static void loginWithCredentials(Page page, String webUrl, String userEmail, String userPassword) {
        SearchPage searchPage = new SearchPage(page);
        LoginPage loginPage = new LoginPage(page);

        searchPage.navigateUrl(webUrl);

        loginPage
                .clickSignIn()
                .waitForLoginModalWindow().isVisible();

        loginPage
                .fillEmailAddress(userEmail)
                .fillPassword(userPassword)
                .clickLoginButton();
        page.waitForLoadState(LoadState.LOAD);
    }

    @Description("Logout from user account")
    static void logoutFromUserAccount(Page page) {
        HomePage homePage = new HomePage(page);

        homePage
                .clickUserAccountNavigationMenu()
                .clickLogout();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}

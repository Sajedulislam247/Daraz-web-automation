package com.daraz.web;

import com.daraz.web.page.SearchPage;
import com.daraz.web.page.LoginPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.ConfigReader;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;

@Epic("User Management")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    String websiteUrl = ConfigReader.getProperty("websiteUrl");
    String userEmail = ConfigReader.getProperty("userEmail");
    String userPassword = ConfigReader.getProperty("userPassword");

    @Test(description = "Verify successful login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Login Flow")
    @Description("Ensures user can login using valid username and password")
    public void loginWithValidCredentialsShouldSucceed() {


        loginWithCredentials(page, websiteUrl, userEmail, userPassword);
        LoginPage loginPage = new LoginPage(page);
        assertThat(loginPage.getMyAccountNevLocator()).containsText("account");
        assertFalse(loginPage.getLoginButtonLocator().isVisible());
    }

    @Description("Login with Credentials")
    public static void loginWithCredentials(Page page, String webUrl, String userEmail, String userPassword) {
        SearchPage searchPage = new SearchPage(page);
        LoginPage loginPage = new LoginPage(page);

        searchPage.navigateUrl(webUrl);
        page.waitForLoadState(LoadState.LOAD);
        loginPage
                .clickSignIn()
                .waitForLoginModalWindow().waitFor();

        loginPage
                .fillEmailAddress(userEmail)
                .fillPassword(userPassword)
                .clickLoginButton();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}

package com.daraz.web.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static io.qameta.allure.Allure.step;

public class HomePage extends BasePage {
    public HomePage(Page page) {
        super(page);
    }

    @Override
    public String getPageID() {
        return "";
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Step("Locate User Account Navigation Menu")
    public Locator getMyAccountNevLocator() {
        step("Locating user account menu");
        return page.locator("#myAccountTrigger");
    }

    @Step("Click on Accounts menu")
    public HomePage clickUserAccountNavigationMenu() {
        getMyAccountNevLocator().click();
        return this;
    }

    @Step("Click Logout menu option")
    public HomePage clickLogout() {
        page.locator("#account-popup-logout").click();
        return this;
    }
}

package lesson6.tests;

import lesson6.pages.AccountPage;
import lesson6.pages.CookieUtils;
import lesson6.pages.HomePage;
import lesson6.pages.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AccountTest  extends  BaseTest{

    @Test
    public  void shouldAcceptCookies() {
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        boolean hasCookie = homePage.hasCookie("OptanonAlertBoxClosed");
        Assert.assertFalse("OptanonAlertBoxClosed cookie not set", hasCookie);

        boolean isBtnDisplayed = homePage.acceptAllCookiesButtonIsDisplayed();
        Assert.assertTrue("Accept cookie button should be visible", isBtnDisplayed);

        homePage.clickAcceptAllCookies();
        hasCookie = homePage.hasCookie("OptanonAlertBoxClosed");
        Assert.assertTrue("OptanonAlertBoxClosed cookie is set", hasCookie);
    }

    @Test
    public  void  successLoginAndLogout() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.navigate();
        Cookie[] cookies = CookieUtils.getAcceptAllCookies();
        homePage.setCookies(cookies);
        homePage.navigate();

        accountPage.navigate();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(loginPage.getPageUrl()));
        Assert.assertEquals("Redirected to login page", loginPage.getPageUrl(), driver.getCurrentUrl());

        boolean isFormDisplayed = loginPage.formIsDisplayed();
        Assert.assertTrue("Login form should be visible", isFormDisplayed);

        loginPage.typeLogin("testbox_22@mail.ru")
                .typePassword("testBox22")
                .sendForm();

        accountPage.navigate();
        boolean isAccountMenuVisible = accountPage.hoverOnAccountMenu().isAccountMenuVisible();
        Assert.assertTrue("Account menu should be visible", isAccountMenuVisible);

        accountPage.clickLogout();
        accountPage.navigate();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(loginPage.getPageUrl()));
        Assert.assertEquals("Redirected to login page", loginPage.getPageUrl(), driver.getCurrentUrl());
    }
}


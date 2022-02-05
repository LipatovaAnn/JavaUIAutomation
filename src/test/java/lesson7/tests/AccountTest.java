package lesson7.tests;

import io.qameta.allure.Description;
import lesson7.pages.AccountPage;
import lesson7.pages.CookieUtils;
import lesson7.pages.HomePage;
import lesson7.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DisplayName("Тестирование авторизации")
public class AccountTest  extends  BaseTest {

    @Test
    @DisplayName("Разрешить все куки")
    @Description("Модальное окно с запросом использования куки файлов должно создать новый куки-файл после нажатия кнопки ОК")
    public void shouldAcceptCookies() {
        HomePage homePage = new HomePage(driver);
        homePage.navigate();

        boolean hasCookie = homePage.hasCookie("OptanonAlertBoxClosed");
        Assertions.assertFalse(hasCookie, "OptanonAlertBoxClosed cookie not set");

        boolean isBtnDisplayed = homePage.acceptAllCookiesButtonIsDisplayed();
        Assertions.assertTrue(isBtnDisplayed, "Accept cookie button should be visible");

        homePage.clickAcceptAllCookies();
        hasCookie = homePage.hasCookie("OptanonAlertBoxClosed");
        Assertions.assertTrue(hasCookie, "OptanonAlertBoxClosed cookie is set");
    }

    @Test
    @DisplayName("Авторизация на сайте и выход из аккаунта")
    public void successLoginAndLogout() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.navigate();
        Cookie[] cookies = CookieUtils.getAcceptAllCookies();
        homePage.setCookies(cookies);
        homePage.navigate();

        accountPage.navigate();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(loginPage.getPageUrl()));
        Assertions.assertTrue(loginPage.getPageUrl().equals(driver.getCurrentUrl()), "Redirected to login page");

        boolean isFormDisplayed = loginPage.formIsDisplayed();
        Assertions.assertTrue(isFormDisplayed, "Login form should be visible");

        loginPage.typeLogin("testbox_22@mail.ru")
                .typePassword("testBox22")
                .sendForm();

        accountPage.navigate();
        boolean isAccountMenuVisible = accountPage.hoverOnAccountMenu().isAccountMenuVisible();
        Assertions.assertTrue(isAccountMenuVisible, "Account menu should be visible");

        accountPage.clickLogout();
        accountPage.navigate();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(loginPage.getPageUrl()));
        Assertions.assertTrue(loginPage.getPageUrl().equals(driver.getCurrentUrl()), "Redirected to login page");
    }
}
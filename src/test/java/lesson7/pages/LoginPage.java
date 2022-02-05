package lesson7.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends  BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private String pageUrl = "https://www2.hm.com/ru_ru/login";

    @FindBy(xpath = "//form[@data-testid='loginForm']")
    private WebElement form;

    @FindBy(id = "email")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @Override
    public String getPageUrl() {
        return pageUrl;
    }

    public boolean formIsDisplayed() {
        return form.isDisplayed();
    }

    @Step("Ввести логин")
    public LoginPage typeLogin(String v) {
        login.sendKeys(v);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage typePassword(String v) {
        password.sendKeys(v);
        return this;
    }

    @Step("Отправить форму")
    public void sendForm() {
        try {
            password.sendKeys(Keys.ENTER);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.urlContains(("/ru_ru/login"))));
        }
        catch (Exception e){
            password.sendKeys(Keys.ENTER);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.urlContains(("/ru_ru/login"))));
        }
    }
}
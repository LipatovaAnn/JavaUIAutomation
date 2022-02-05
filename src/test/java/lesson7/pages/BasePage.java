package lesson7.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Загрузить страницу")
    public void navigate() {
        driver.get(getPageUrl());
    }

    public  boolean  hasCookie(String cookieName                            ) {
        Cookie c = driver.manage().getCookieNamed(cookieName);
        return c != null;
    }

    public abstract String getPageUrl();

    public void setCookies(Cookie[] cookies) {
        for (Cookie c : cookies) {
            driver.manage().addCookie(c);
        }
    }
}

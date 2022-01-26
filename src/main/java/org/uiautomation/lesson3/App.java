package org.uiautomation.lesson3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;
import java.util.function.Function;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty(
                "webdriver.chrome.driver", "src/main/resources/chromedriver.exe"
        );
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(900, 900));
        try {
            loginLogout(driver);
            search(driver);
            addToCart(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        } finally {
           // Scanner s = new Scanner(System.in);
            //s.nextLine();
            driver.quit();
        }
    }

    public static void acceptCookies(WebDriver driver) {
        try {
            WebElement cookieBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
            if (cookieBtn.isDisplayed()) {
                cookieBtn.click();
            }
        } catch (Exception e) {
        }
    }

    public static void loginLogout(WebDriver driver) throws InterruptedException {
        driver.get("https://www2.hm.com/ru_ru/login");

        acceptCookies(driver);

        WebElement btn = driver.findElement(By.xpath("//a[contains(.,'Войти')]"));
        btn.click();

        WebElement form = driver.findElement(By.id("modal-theLoginForm"));
        form.findElement(By.id("modal-txt-signin-email")).click();

        form.findElement(By.id("modal-txt-signin-email")).sendKeys("testbox_22@mail.ru");
        form.findElement(By.id("modal-txt-signin-password")).click();
        form.findElement(By.id("modal-txt-signin-password")).sendKeys("testBox22");
        form.findElement(By.id("modal-txt-signin-password")).sendKeys(Keys.ENTER);

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/ru_ru/account"));
        WebElement myAcc = driver.findElement(By.xpath("//a[contains(text(),'Мой аккаунт')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement((myAcc));
        actions.perform();
        WebElement logout = driver.findElement(By.xpath("//a[contains(@href, '/ru_ru/logout')]"));
        logout.click();
    }

    public static void search(WebDriver driver) {
        driver.get("https://www2.hm.com/ru_ru/index.html");

        acceptCookies(driver);

        WebElement c = driver.findElement(By.xpath("//button[contains(@class,'menu__search_toggle')]"));
        if (c.isDisplayed()) {
            c.click();
        }

        c = driver.findElement(By.id("main-search"));
        c.sendKeys("шапка");
        c.sendKeys(Keys.ENTER);
        c = driver.findElement(By.xpath("//div[@id='page-content']/div/ul/li/article/div/a/img"));
        c.click();
    }

    public static void addToCart(WebDriver driver) {
        driver.get("https://www2.hm.com/ru_ru/index.html");

        acceptCookies(driver);
        WebElement btn = driver.findElement(By.xpath("//a[contains(.,'Войти')]"));
        btn.click();

        WebElement form = driver.findElement(By.id("modal-theLoginForm"));
        form.findElement(By.id("modal-txt-signin-email")).click();

        form.findElement(By.id("modal-txt-signin-email")).sendKeys("testbox_22@mail.ru");
        form.findElement(By.id("modal-txt-signin-password")).click();
        form.findElement(By.id("modal-txt-signin-password")).sendKeys("testBox22");
        form.findElement(By.id("modal-txt-signin-password")).sendKeys(Keys.ENTER);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-theLoginForm")));

        driver.get("https://www2.hm.com/ru_ru/index.html");

        WebElement c = driver.findElement(By.xpath("//button[contains(@class,'menu__trigger')]"));
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(c));
        c.click();
        c = driver.findElement(By.xpath("//span[contains(.,'Женщины')]"));
        c.click();
        c = c.findElement(By.xpath("//span[contains(.,'Новые поступления')]"));
        c.click();
        c = c.findElement(By.xpath("//a[contains(text(),'Обувь и аксессуары')]"));
        c.click();

        c = driver.findElement(By.xpath("//div[@id='page-content']//article//a/img"));
        c.click();

        c = driver.findElement(By.xpath("//span[contains(., 'Выбрать размер')]"));
        if (c.isDisplayed()) {
            c.click();

            c = driver.findElement(By.xpath("//div[contains(@class,'picker-option')]/../../node()[2]//div"));
            c.click();
        }

        c = driver.findElement(By.xpath("//button[contains(@class,'button-buy')]"));
        c.click();

    }
}


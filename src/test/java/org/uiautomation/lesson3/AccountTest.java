package org.uiautomation.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class AccountTest {

    WebDriver driver;

    @BeforeAll
    static  void  setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void  setupTest(){
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(900, 900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

   private static  Cookie[] getAcceptAllCookie() {
        Cookie[] cookies=new Cookie[2];
       try {
           Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2034-12-31");
           cookies[0] = new Cookie("OptanonAlertBoxClosed", "2022-01-29T15:35:18.916Z", ".hm.com", "/", d,false,false,"Lax");
           cookies[1] = new Cookie("OptanonConsent", "isGpcEnabled=0&datestamp=Sun+Jan+30+2022+12%3A31%3A12+GMT%2B0300+(%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%2C+%D1%81%D1%82%D0%B0%D0%BD%D0%B4%D0%B0%D1%80%D1%82%D0%BD%D0%BE%D0%B5+%D0%B2%D1%80%D0%B5%D0%BC%D1%8F)&version=6.23.0&isIABGlobal=false&hosts=&consentId=c093faca-08f4-47ef-ba47-22067c83a6ba&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1", ".hm.com", "/", d,false,false,"Lax");
       } catch (Exception e) {
       }

       return cookies;
   }

    @Test
    public  void shouldAcceptCookies() {
        driver.get("https://www2.hm.com/ru_ru/login");
        WebElement cookieBtn = driver.findElement(By.id("onetrust-accept-btn-handler"));
        Cookie c =  driver.manage().getCookieNamed("OptanonAlertBoxClosed");
        Assert.assertNull("OptanonAlertBoxClosed cookie not set", c);
        Assert.assertTrue("Accept cookie button should be visible", cookieBtn.isDisplayed());
        cookieBtn.click();
        c =  driver.manage().getCookieNamed("OptanonAlertBoxClosed");
        Assert.assertNotNull("OptanonAlertBoxClosed cookie is set", c);
    }

    @Test
    public  void  successLoginAndLogout() {
        driver.get("https://www2.hm.com/ru_ru/");
        Cookie[] cookies = getAcceptAllCookie();
        for (Cookie c : cookies) {
            driver.manage().addCookie(c);
        }

        driver.get("https://www2.hm.com/ru_ru/account");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/ru_ru/login"));
        Assert.assertEquals("Redirected to login page", "https://www2.hm.com/ru_ru/login", driver.getCurrentUrl());

        WebElement form = driver.findElement(By.xpath("//form[@data-testid='loginForm']"));
        Assert.assertTrue("Login form should be visible", form.isDisplayed());

        form.findElement(By.id("email")).sendKeys("testbox_22@mail.ru");
        form.findElement(By.id("password")).sendKeys("testBox22");

        try {
            form.findElement(By.id("password")).sendKeys(Keys.ENTER);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.urlContains(("/ru_ru/login"))));
        }
        catch (Exception e){
            form.findElement(By.id("password")).sendKeys(Keys.ENTER);
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.urlContains(("/ru_ru/login"))));
        }

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Мой аккаунт')]")));
        WebElement myAcc = driver.findElement(By.xpath("//a[contains(text(),'Мой аккаунт')]"));
        Actions builder = new Actions(driver);
        builder.moveToElement((myAcc));
        builder.build().perform();

        WebElement accountMenu = driver.findElement(By.xpath("//div[contains(@class,'myhm-dropmenu')][@data-signin-state='signedin']"));
        Assert.assertTrue("Account menu should be visible", accountMenu.isDisplayed());
        WebElement logout = accountMenu.findElement(By.xpath("//a[contains(@href, '/ru_ru/logout')]"));
        logout.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.urlContains(("/ru_ru/account"))));

        driver.get("https://www2.hm.com/ru_ru/account");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/ru_ru/login"));
        Assert.assertEquals("Redirected to login page", "https://www2.hm.com/ru_ru/login", driver.getCurrentUrl());
    }
}


package lesson6.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract  class BaseTest {
   protected  WebDriver driver;

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

}

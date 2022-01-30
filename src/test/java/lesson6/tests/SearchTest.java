package lesson6.tests;

import lesson6.pages.CookieUtils;
import lesson6.pages.SearchPage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTest extends  BaseTest{

    @Test
    public  void  shouldSearchItem() {
        SearchPage page = new SearchPage(driver);
        page.navigate();
        page.setCookies(CookieUtils.getAcceptAllCookies());
        page.navigate();

        boolean isVisible = page.openSearch().isSearchInputVisible();
        Assert.assertTrue("Search input should be visible", isVisible);

        boolean hasResult = page.searchFor("шапка").hasSearchResults();
        Assert.assertTrue("Has search results", hasResult);

        try {
            page.clickFirstResult();
        }catch (Exception e) {
            Assert.fail("Cant click search result");
        }
    }
}


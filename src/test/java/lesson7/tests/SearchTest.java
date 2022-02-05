package lesson7.tests;

import lesson7.pages.CookieUtils;
import lesson7.pages.SearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование поиска")
public class SearchTest extends  BaseTest{

    @Test
    @DisplayName("Поиск товара")
    public  void  shouldSearchItem() {
        SearchPage page = new SearchPage(driver);
        page.navigate();
        page.setCookies(CookieUtils.getAcceptAllCookies());
        page.navigate();

        boolean isVisible = page.openSearch().isSearchInputVisible();
        Assertions.assertTrue(isVisible, "Search input should be visible");

        boolean hasResult = page.searchFor("шапка").hasSearchResults();
        Assertions.assertTrue(hasResult, "Has search results");
        page.clickFirstResult();
    }
}


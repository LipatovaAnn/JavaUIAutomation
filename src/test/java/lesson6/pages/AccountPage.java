package lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends  BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private String pageUrl = "https://www2.hm.com/ru_ru/account";

    @FindBy(xpath = "//div[contains(@class,'myhm-dropmenu')][@data-signin-state='signedin']")
    private WebElement accountMenu;

    @FindBy(xpath ="//a[contains(@href, '/ru_ru/logout')]" )
    private   WebElement logout;

    @Override
    public String getPageUrl() {
        return pageUrl;
    }

    public AccountPage hoverOnAccountMenu() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Мой аккаунт')]")));
        WebElement myAcc = driver.findElement(By.xpath("//a[contains(text(),'Мой аккаунт')]"));
        Actions builder = new Actions(driver);
        builder.moveToElement((myAcc));
        builder.build().perform();
        return this;
    }

    public boolean isAccountMenuVisible() {
        return accountMenu.isDisplayed();
    }

    public  void clickLogout(){
        logout.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.not(ExpectedConditions.urlContains((pageUrl))));
    }
}

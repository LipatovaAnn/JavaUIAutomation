package lesson6.pages;

import org.openqa.selenium.Cookie;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CookieUtils {
    public  static Cookie[] getAcceptAllCookies(){
        Cookie[] cookies=new Cookie[2];
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse("2034-12-31");
            cookies[0] = new Cookie("OptanonAlertBoxClosed", "2022-01-29T15:35:18.916Z", ".hm.com", "/", d,false,false,"Lax");
            cookies[1] = new Cookie("OptanonConsent", "isGpcEnabled=0&datestamp=Sun+Jan+30+2022+12%3A31%3A12+GMT%2B0300+(%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%2C+%D1%81%D1%82%D0%B0%D0%BD%D0%B4%D0%B0%D1%80%D1%82%D0%BD%D0%BE%D0%B5+%D0%B2%D1%80%D0%B5%D0%BC%D1%8F)&version=6.23.0&isIABGlobal=false&hosts=&consentId=c093faca-08f4-47ef-ba47-22067c83a6ba&interactionCount=1&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1", ".hm.com", "/", d,false,false,"Lax");
        } catch (Exception e) {
        }

        return cookies;
    }
}

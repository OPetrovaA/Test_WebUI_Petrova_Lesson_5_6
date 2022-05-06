import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

/**
 * Avtom.WebUI Petrova Olga
 */

public class PostAdsPage {
    private static ChromeDriver driver;
    private By btnPostAds = By.xpath("//a/span[text()='+ Разместить объявление']");
    private By btnOwner = By.xpath("//div[@data-mark='switcher_button||true']");
    String expClassRadioBtn = "cui-switcher__part ng-untouched ng-valid cui-switcher__part_state_active ng-not-empty ng-dirty ng-valid-parse";

    @BeforeEach
    public void setUd() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        // options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    @DisplayName("Проверяем работает ли ссылка на кнопке '+Разместить объявление'")
    @Severity(SeverityLevel.CRITICAL)
    public void clickPostAds(){
        DataPostAdsPage getDataPostAdsPage = new DataPostAdsPage();
        driver.get(getDataPostAdsPage.getUrl1);
        driver.findElement(btnPostAds).click();
        String title = driver.getTitle();
        Assertions.assertTrue(title.equals("Разместить объявление о продаже недвижимости на ЦИАН, подать объявление о продаже или аренде недвижимости бесплатно - ЦИАН"));
    }

    @Test
    @DisplayName("Проверяем правильно ли работает радио-батн")
    @Severity(SeverityLevel.MINOR)
    public void radioBtn() throws InterruptedException {
        DataPostAdsPage getDataPostAdsPage = new DataPostAdsPage();
        driver.get(getDataPostAdsPage.getUrl2);
        driver.findElement(btnOwner).click();
        Thread.sleep(400);
        String paract = driver.findElement(btnOwner).getAttribute("className");
        Assertions.assertEquals(expClassRadioBtn,paract, "Параметры радиоБтн не изменились, тест провален");


    }
}

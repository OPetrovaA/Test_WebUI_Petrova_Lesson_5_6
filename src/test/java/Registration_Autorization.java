import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

/**
 * Avtom.WebUI Petrova Olga
 */

public class Registration_Autorization {
        private static ChromeDriver driver;
        private By btnVoity = By.xpath("//a[@href='/authenticate/?returnUrl=https://www.cian.ru/']");
        private By btnDrugoySposob = By.xpath(".//button[@data-name='SwitchToEmailAuthBtn']");
        private By inputLogEmail = By.xpath(".//input[@name='username']");
        private By btnContinue = By.xpath("//button[@data-name='ContinueAuthBtn']");
        private By checkboxOk = By.xpath(".//span[@class='_25d45facb5--box--FHmxp _25d45facb5--box--LJe53']");
        private By btnContinue2check = By.xpath("//button[@data-name='ContinueBtn']");
        private By newPassword = By.xpath("//input[@autocomplete='new-password']");
        private By inputPassword = By.name("password");
        private By btnVoityFinish = By.xpath(".//div[@class='_25d45facb5--buttons--f0vot']");
        private By userAvatar = By.xpath(".//a[@data-name='UserAvatar']");
        private By actualIdLogin = By.xpath("//a[@class='_25d45facb5--full-name--K5jY5']");
        private By stepEmail = By.xpath("//div[@class='register_step_content']");
        private By actualRegOk = By.xpath("//h3[@class='_25d45facb5--title--OSQN1 _25d45facb5--large_indent--YwY6z']");
        private By errorPassActual = By.xpath("//span[@class='_25d45facb5--error-message--NCPjP']");
        String myIdLogin = "Ваш E-mail: yagejo7360@moonran.com";
        String regTextOk = "Завершение регистрации";
        String errorPass = "Пароль должен содержать не менее 8 и не более 30 символов";


        @BeforeEach
        public void setUd() {
            Data getData = new Data();
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            // options.addArguments("--headless");
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(getData.getUrl);
            String title = driver.getTitle();
            Assertions.assertTrue(title.equals("Циан - база недвижимости в Московской области | Продажа, аренда квартир и другой недвижимости"));
        }

        @Test
        public void registration() throws InterruptedException {
            Data getData = new Data();
            driver.findElement(btnVoity).click();
            driver.findElement(btnDrugoySposob).click();
            driver.findElement(inputLogEmail).sendKeys(getData.newLogEmail);
            driver.findElement(btnContinue).click();
            driver.findElement(checkboxOk).click();
            driver.findElement(btnContinue2check).click();
            driver.findElement(newPassword).sendKeys(getData.password);
            driver.findElement(btnVoityFinish).click();
            Thread.sleep(3000);
            String paramet = driver.findElement(actualRegOk).getAttribute("innerText");
            System.out.println(paramet);
            Assertions.assertEquals(regTextOk, paramet, "Registration failed");

        }

        @Test
        public void autorization() throws InterruptedException {
            Data getData = new Data();
            driver.findElement(btnVoity).click();
            driver.findElement(btnDrugoySposob).click();
            driver.findElement(inputLogEmail).sendKeys(getData.logEmail);
            driver.findElement(btnContinue).click();
            driver.findElement(inputPassword).sendKeys(getData.password);
            driver.findElement(btnVoityFinish).click();
            driver.findElement(userAvatar).click();
            driver.findElement(actualIdLogin).click();
            String parametr = driver.findElement(stepEmail).getAttribute("innerText");
            System.out.println(parametr);
            Assertions.assertEquals(myIdLogin, parametr, "Authorization failed");

        }

        @Test  //тест показывает, что с невалидным логином мы не получим инпут ввода пароля
        void negativAutorizLog() {
            Data getData = new Data();
            driver.findElement(btnVoity).click();
            driver.findElement(btnDrugoySposob).click();
            driver.findElement(inputLogEmail).sendKeys(getData.negativeLogEmail);
            driver.findElement(btnContinue).click();
            try {
                WebElement inputPassword = driver.findElement(By.name("password"));
            } catch (NoSuchElementException e) {
                System.out.println("невалидный логин не принят, тест успешен");
            }

        }


        @Test
        void negPassRegistration() throws InterruptedException {
            Data getData = new Data();
            driver.findElement(btnVoity).click();
            driver.findElement(btnDrugoySposob).click();
            driver.findElement(inputLogEmail).sendKeys(getData.newLogEmail2);
            driver.findElement(btnContinue).click();
            driver.findElement(checkboxOk).click();
            driver.findElement(btnContinue2check).click();
            driver.findElement(newPassword).sendKeys(getData.negativpassword);
            driver.findElement(btnVoityFinish).click();
            Thread.sleep(1000);
            String errorP = driver.findElement(errorPassActual).getAttribute("innerHTML");
            Assertions.assertEquals(errorPass, errorP, "Error text is invalid or there is no error text");


        }

        // @AfterEach
        // public void testClose() throws InterruptedException {
        //     Thread.sleep(10000);
        //      driver.quit();
        // }

    }



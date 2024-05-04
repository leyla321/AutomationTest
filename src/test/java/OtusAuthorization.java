import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OtusAuthorization {
    private final static Logger logger = LogManager.getLogger(OtusAuthorization.class);
    private WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        String url = System.getProperty("url");
        driver.get("url");
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null)
            driver.close();
        driver.quit();
    }

    @Test
    public void loginToOtus() {
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        WebElement signInButton = driver.findElement(By.cssSelector("button.sc-mrx253-0.enxKCy.sc-945rct-0.iOoJwQ"));
        signInButton.click();
        WebElement email = driver.findElement(By.xpath("(//*[contains(.,'Электронная почта')])[2]"));
        email.click();
        email.sendKeys(username);
        WebElement password2 = driver.findElement(By.cssSelector(".sc-177u1yy-0.sc-rq8xzv-2.xkNdd"));
        password2.click();
        password2 = driver.findElement(By.cssSelector("input[type='password']"));
        password2.sendKeys(password);
        WebElement signInButton2 = driver.findElement(By.cssSelector(".sc-9a4spb-0.eQlGvH.sc-11ptd2v-2-Component.cElCrZ"));
        signInButton2.click();
        logger.info("Авторизация прошла успешно");
        logger.trace(driver);
    }
}

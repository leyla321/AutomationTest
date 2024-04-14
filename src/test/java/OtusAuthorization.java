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
        driver.get("https://otus.ru");
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }

    @Test
    public void loginToOtus() {
        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Войти']"));
        signInButton.click();
        WebElement email = driver.findElement(By.xpath("//div[./input[@name='email']]"));
        email.click();
        email = driver.findElement(By.xpath("//input[@name='email']"));
        email.sendKeys("otus51423@mailto.plus");
        WebElement password = driver.findElement(By.xpath("//div[./input[@type='password']]"));
        password.click();
        password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("12345678Qw1!");
        WebElement signInButton2 = driver.findElement(By.xpath("//button[./*[text()='Войти']]"));
        signInButton2.click();
        logger.trace(driver);
    }
}

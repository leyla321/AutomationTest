import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class KioskMode {


    private WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        driver = new ChromeDriver(options);
        String url = System.getProperty("url");
        driver.get(url);
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void openBrowserInMaximizedSize() {
        WebElement selectPic = driver.findElement(By.xpath("(//div[@class='content-overlay'])[2]"));
        selectPic.click();
        WebElement openedPic = driver.findElement(By.cssSelector(".pp_content_container"));
        Assertions.assertTrue(openedPic.isDisplayed(), "Clicked picture is not opened");
    }
}

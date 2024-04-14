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
        driver.manage().window().fullscreen();
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }

    @Test
    public void openBrowserInMaximizedSize() {
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        WebElement selectPic = driver.findElement(By.xpath("(//div[@class='content-overlay'])[2]"));
        selectPic.click();
        WebElement openedPic = driver.findElement(By.xpath("//div[@class='pp_pic_holder light_rounded']"));
        Assertions.assertTrue(openedPic.isDisplayed(), "Clicked picture is not opened.");
    }
}

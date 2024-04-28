import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverTest {
    private org.openqa.selenium.WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        String url = System.getProperty("url");
        driver.get(url);
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }

    @Test
    public void openBrowserInHeadlessFormat() {
        WebElement searchInput = driver.findElement(By.cssSelector("#searchbox_input"));
        searchInput.sendKeys("ОТУС");
        WebElement enterButton = driver.findElement(By.cssSelector("button[type='submit']"));
        enterButton.click();
        WebElement firstResult = driver.findElement(By.cssSelector("#r1-0 > div.ikg2IXiCD14iVX7AdZo1 > h2 > a"));
        String firstResultText = firstResult.getText();
        String expectedResult = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        Assertions.assertEquals(expectedResult, firstResultText);
    }

}

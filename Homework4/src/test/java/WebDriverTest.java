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
    }

    @AfterEach
    public void closeDriver() {
        if (driver != null)
            driver.close();
    }

    @Test
    public void openBrowserInHeadlessFormat() {
        driver.get("https://duckduckgo.com/");
        WebElement searchInput = driver.findElement(By.id("searchbox_input"));
        searchInput.sendKeys("ОТУС");
        WebElement enterButton = driver.findElement(By.xpath("//button[@type='submit']"));
        enterButton.click();
        WebElement firstResult = driver.findElement(By.xpath("//span[@class='EKtkFWMYpwzMKOYr0GYm LQVY1Jpkk8nyJ6HBWKAk' and contains(text(),'Онлайн‑курсы для профессионалов, дистанционное обучение современным ...')]"));
        String firstResultText = firstResult.getText();
        String expectedResult = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        Assertions.assertEquals(expectedResult, firstResultText);
    }

}

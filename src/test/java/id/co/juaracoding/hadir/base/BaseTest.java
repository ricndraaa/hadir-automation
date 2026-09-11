package id.co.juaracoding.hadir.base;

import id.co.juaracoding.hadir.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Kelas induk semua test Selenium.
 * Tugasnya: membuka browser sebelum tiap test, dan menutupnya setelah tiap test.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    public void bukaBrowser() {
        baseUrl = ConfigReader.getBaseUrl();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @AfterMethod(alwaysRun = true)
    public void tutupBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void bukaHalaman(String path) {
        driver.get(baseUrl + path);
    }
}
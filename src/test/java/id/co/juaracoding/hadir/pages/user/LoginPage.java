package id.co.juaracoding.hadir.pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object halaman Login User (mobile mirroring) — /absen/login
 * Locator bersumber dari docs/locators/user-login-page.md
 */
public class LoginPage {

    public static final String PATH = "/absen/login";
    public static final String PATH_SETELAH_LOGIN = "/apps/absent";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput      = By.id("email");
    private final By passwordInput   = By.id("password");
    private final By submitButton    = By.cssSelector("button[type='submit']");
    private final By errorAlertIcon  = By.cssSelector("[role='alert'] svg[data-testid='ErrorOutlineIcon']");
    private final By alertMessage    = By.cssSelector("[role='alert'] .MuiAlert-message p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void isiEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    public void isiPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void klikMasuk() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void loginSebagai(String email, String password) {
        isiEmail(email);
        isiPassword(password);
        klikMasuk();
    }

    public void tungguSampaiPindahKeHalamanAbsen() {
        wait.until(ExpectedConditions.urlContains(PATH_SETELAH_LOGIN));
    }

    public Boolean isAlertErrorTampil() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlertIcon));
            return Boolean.TRUE;
        } catch (org.openqa.selenium.TimeoutException e) {
            return Boolean.FALSE;
        }
    }

    /** Hanya untuk logging di laporan. ⛔ JANGAN dipakai sebagai assertion. */
    public String bacaPesanAlert() {
        return driver.findElement(alertMessage).getText();
    }
}
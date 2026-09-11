package id.co.juaracoding.hadir.tests.user;

import id.co.juaracoding.hadir.base.BaseTest;
import id.co.juaracoding.hadir.config.ConfigReader;
import id.co.juaracoding.hadir.pages.user.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = "smoke")
    public void should_redirect_to_halaman_absen_when_login_valid() {
        bukaHalaman(LoginPage.PATH);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginSebagai(ConfigReader.getUserEmail(), ConfigReader.getUserPassword());
        loginPage.tungguSampaiPindahKeHalamanAbsen();

        Assert.assertTrue(driver.getCurrentUrl().contains(LoginPage.PATH_SETELAH_LOGIN),
                "Login valid harus pindah ke " + LoginPage.PATH_SETELAH_LOGIN
                        + ". URL aktual: " + driver.getCurrentUrl());

        Assert.assertFalse(loginPage.isAlertErrorTampil(),
        "Login BERHASIL seharusnya TIDAK memunculkan alert error");
    }

    @Test(priority = 2, groups = "smoke")
    public void should_show_alert_error_when_password_salah() {
        bukaHalaman(LoginPage.PATH);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginSebagai(ConfigReader.getUserEmail(), "SalahBanget123!");

        Assert.assertTrue(loginPage.isAlertErrorTampil(),
                "Alert error harus muncul saat password salah");

        Assert.assertTrue(driver.getCurrentUrl().contains(LoginPage.PATH),
                "Login gagal TIDAK boleh pindah halaman. URL aktual: " + driver.getCurrentUrl());
    }

    @Test(priority = 3, groups = "regression")
    public void should_show_alert_error_when_email_tidak_terdaftar() {
        bukaHalaman(LoginPage.PATH);

        String emailTidakTerdaftar = "tidakada" + System.currentTimeMillis() + "@example.com";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginSebagai(emailTidakTerdaftar, ConfigReader.getUserPassword());

        Assert.assertTrue(loginPage.isAlertErrorTampil(),
                "Alert error harus muncul saat email tidak terdaftar");
    }
}
package id.co.juaracoding.hadir;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test dummy Tahap 0.
 * Tujuannya HANYA membuktikan rantai Maven -> Surefire -> TestNG sudah tersambung.
 * Belum menyentuh browser sama sekali.
 */
public class SanityTest {

    @BeforeClass
    public void tampilkanInfoLingkungan() {
        System.out.println("[@BeforeClass] Java version : " + System.getProperty("java.version"));
        System.out.println("[@BeforeClass] OS           : " + System.getProperty("os.name"));
    }

    @Test
    public void should_pass_when_setup_maven_benar() {
        Boolean setupBerhasil = Boolean.TRUE;
        Assert.assertTrue(setupBerhasil, "Kalau baris ini gagal, ada yang salah di TestNG");
    }

    @Test
    public void should_return_21_when_membaca_versi_java() {
        String versiJava = System.getProperty("java.version");
        Assert.assertTrue(versiJava.startsWith("21"),
                "Project ini dirancang untuk Java 21. Versi terbaca: " + versiJava);
    }
}
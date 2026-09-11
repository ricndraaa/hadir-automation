package id.co.juaracoding.hadir.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Pembaca file config.properties.
 * Dibuat static supaya file hanya dibaca SEKALI selama seluruh suite berjalan.
 */
public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();
    private static final String FILE_NAME = "config.properties";

    // Blok static: dijalankan otomatis SEKALI, saat kelas ini pertama kali disentuh.
    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(FILE_NAME)) {

            if (input == null) {
                throw new RuntimeException(
                        "File " + FILE_NAME + " tidak ditemukan di src/test/resources. " +
                        "Salin config.properties.example menjadi config.properties, lalu isi nilainya.");
            }
            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Gagal membaca " + FILE_NAME, e);
        }
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Key '" + key + "' kosong atau tidak ada di " + FILE_NAME);
        }
        return value;
    }

    public static String getBaseUrl()      { return get("base.url"); }
    public static String getUserEmail()    { return get("user.email"); }
    public static String getUserPassword() { return get("user.password"); }
}
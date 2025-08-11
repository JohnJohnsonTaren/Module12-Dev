package storage;

import org.flywaydb.core.Flyway;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseInitService {
    public void initDb() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("hibernate.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        String connectionUrl = properties.getProperty("hibernate.connection.url");

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();
    }
}
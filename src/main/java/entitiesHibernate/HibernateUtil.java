package entitiesHibernate;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    public HibernateUtil() {
        try {
            Configuration configuration = new Configuration();

            // Завантажуємо властивості з hibernate.properties
            Properties properties = new Properties();
            properties.load(HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("hibernate.properties"));

            configuration.setProperties(properties);

            // Додаємо додаткові налаштування
            configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            sessionFactory = configuration
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Не вдалося створити SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}
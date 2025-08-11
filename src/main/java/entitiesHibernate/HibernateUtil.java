package serviceCRUD;

import entitiesHibernate.Client;
import entitiesHibernate.Planet;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import storage.DatabaseInitService;

import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration();
            
            // Завантажуємо властивості з hibernate.properties
            Properties properties = new Properties();
            properties.load(HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("hibernate.properties"));
            
            configuration.setProperties(properties);
            
            // Для production використовуємо validate, щоб не перезаписувати дані
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
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
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        // Ініціалізація БД за допомогою flyway
        new DatabaseInitService().initDb();
        
        HibernateUtil util = HibernateUtil.getInstance();

        // Приклад створення клієнта
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
            Client newClient = new Client();
            newClient.setName("Test Client");
            session.persist(newClient);
            System.out.println("newClient = " + newClient);
        transaction.commit();
        session.close();

        // Приклад читання всіх клієнтів
        session = util.getSessionFactory().openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        System.out.println("clients = " + clients);
        session.close();

        // Приклад створення планети
        session = util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
            Planet newPlanet = new Planet();
            newPlanet.setId("MARS");
            newPlanet.setName("Mars Planet");
            session.persist(newPlanet);
            System.out.println("newPlanet = " + newPlanet);
        transaction.commit();
        session.close();

        // Приклад читання всіх планет
        session = util.getSessionFactory().openSession();
        List<Planet> planets = session.createQuery("from Planet", Planet.class).list();
        System.out.println("planets = " + planets);
        session.close();

        util.close();
    }
}
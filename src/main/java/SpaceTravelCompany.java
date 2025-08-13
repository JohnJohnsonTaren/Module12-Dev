//Ти працюєш в компанії SpaceTravel. Компанія займається перевезенням пасажирів між планетами.
//
//  Відповідно, є наступні сутності з наступними зв'язками:
//      Client (клієнт) - клієнт компанії. Має наступні властивості:
//          id - ідентифікатор, первинний сурогатний ключ, автоінкрементне число.
//          name - ім'я, від 3 до 200 символів включно
//      Planet (планета). Початковий або кінцевий пункт відправлення. Має наступні властивості:
//          id - ідентифікатор планети.
//              Рядок, що складається виключно з латинських букв у верхньому регістрі та цифр. Наприклад, MARS, VEN
//          name - назва планети, рядок від 1 до 500 символів включно
//      Ticket (квиток). Має наступні властивості:
//          id - ідентифікатор квитка, первинний сурогатний ключ, автоінкрементне число.
//          created_at - TIMESTAMP в UTC, коли був створений цей квиток
//          client_id - ідентифікатор клієнта, якому належить цей квиток.
//          from_planet_id - ідентифікатор планети, звідки відправляється пасажир
//          to_planet_id - ідентифікатор планети, куди летить пасажир

import utilPackage.HibernateUtil;
import entitiesHibernate.Client;
import entitiesHibernate.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import storage.DatabaseInitService;
import java.util.List;

public class SpaceTravelCompany {
    public static void main(String[] args) {
        new DatabaseInitService().initDb();

        HibernateUtil util = HibernateUtil.getInstance();

        System.out.println("SpaceTravel Company application started successfully!");

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

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            util.close();
            System.out.println("Application shutdown complete.");
        }));
    }
}
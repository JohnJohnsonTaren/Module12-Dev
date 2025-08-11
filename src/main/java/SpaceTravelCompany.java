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

import entitiesHibernate.HibernateUtil;
import storage.DatabaseInitService;

public class SpaceTravelCompany {
    public static void main(String[] args) {
        new DatabaseInitService().initDb();

        HibernateUtil hibernateUtil = HibernateUtil.getInstance();

        System.out.println("SpaceTravel Company application started successfully!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            hibernateUtil.close();
            System.out.println("Application shutdown complete.");
        }));
    }
}

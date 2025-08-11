package serviceCRUD;

import entitiesHibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import entitiesHibernate.Client;

public class ClientCrudService {
    private final HibernateUtil hibernateUtil;

    public ClientCrudService (HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public void create(Client client) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }
    }

    public Client read(Long id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        }
    }

    public void update(Client client) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        }
    }
    public void delete(Client client) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        }
    }
    public List<Client> listAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

}

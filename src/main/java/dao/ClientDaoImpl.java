package dao;

import entitiesHibernate.Client;
import entitiesHibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private final HibernateUtil hibernateUtil;

    public ClientDaoImpl(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @Override
    public void save(Client client) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw  new RuntimeException("Помилка при збереженні клієнта: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Client findById(Long id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        } catch (Exception ex) {
            throw new RuntimeException("Помилка при пошуку клієнта за ID " + id + ": " + ex.getMessage(), ex);
        }

    }

    @Override
    public void update(Client client) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(client);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при оновленні клієнта: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Client client) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при видаленні клієнта: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Client> findAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        } catch (Exception ex) {
            throw new RuntimeException("Помилка при отриманні всіх клієнтів: " + ex.getMessage(), ex);
        }
    }
}
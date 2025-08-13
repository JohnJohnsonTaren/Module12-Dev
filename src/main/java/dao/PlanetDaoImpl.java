package dao;

import entitiesHibernate.Planet;
import utilPackage.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PlanetDaoImpl implements PlanetDao {
    private final HibernateUtil hibernateUtil;

    public PlanetDaoImpl(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @Override
    public void save(Planet planet) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при збереженні планети: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Planet findById(String id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        } catch (Exception ex) {
            throw new RuntimeException("Помилка при пошуку планети за ID " + id + ": " + ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Planet planet) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при оновленні планети: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(Planet planet) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Помилка при видаленні планети: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<Planet> findAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        } catch (Exception ex) {
            throw new RuntimeException("Помилка при отриманні всіх планет: " + ex.getMessage(), ex);
        }
    }
}
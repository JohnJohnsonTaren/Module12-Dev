package serviceCRUD;

import entitiesHibernate.HibernateUtil;
import entitiesHibernate.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PlanetCrudService {
    private final HibernateUtil hibernateUtil;

    public PlanetCrudService(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public void create(Planet planet) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        }
    }

    public Planet read(String id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public void update(Planet planet) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        }
    }

    public void delete(Planet planet) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }

    public List<Planet> listAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }
}
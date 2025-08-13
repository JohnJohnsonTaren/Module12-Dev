package dao;

import entitiesHibernate.Planet;

import java.util.List;

public interface PlanetDao {
    void save(Planet planet);

    Planet findById(String id);

    void update(Planet planet);

    void delete(Planet planet);

    List<Planet> findAll();
}

package serviceCRUD;

import entitiesHibernate.Planet;

import java.util.List;

public interface PlanetService {
    void create(Planet planet);

    Planet read(String id);

    void update(Planet planet);

    void delete(Planet planet);

    List<Planet> listAll();
}

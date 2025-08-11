package serviceCRUD;

import entitiesHibernate.HibernateUtil;
import entitiesHibernate.Planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudServiceTest {
    private PlanetCrudService planetService;

    @BeforeEach
    void setUp() {
        planetService = new PlanetCrudService(new HibernateUtil());
    }

    @Test
    void create() {
        Planet planet = new Planet();
        planet.setId("TEST1");
        planet.setName("Test Planet");
        planetService.create(planet);
    }

    @Test
    void read() {
        Planet planet = new Planet();
        planet.setId("TEST2");
        planet.setName("Test Planet");
        planetService.create(planet);
        
        Planet readPlanet = planetService.read("TEST2");
        assertEquals("Test Planet", readPlanet.getName());
    }

    @Test
    void update() {
        Planet planet = new Planet();
        planet.setId("TEST3");
        planet.setName("Test Planet");
        planetService.create(planet);
        
        planet.setName("Updated Planet");
        planetService.update(planet);
        
        Planet updatedPlanet = planetService.read("TEST3");
        assertEquals("Updated Planet", updatedPlanet.getName());
    }

    @Test
    void delete() {
        Planet planet = new Planet();
        planet.setId("TEST4");
        planet.setName("Test Planet");
        planetService.create(planet);
        
        planetService.delete(planet);
        assertNull(planetService.read("TEST4"));
    }
}
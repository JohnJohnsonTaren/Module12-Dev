package serviceCRUD;

import dao.PlanetDao;
import entitiesHibernate.Planet;
import java.util.List;

public class PlanetCrudService implements PlanetService {
    private final PlanetDao planetDao;

    public PlanetCrudService(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }

    @Override
    public void create(Planet planet) {
        validatePlanet(planet);
        
        planetDao.save(planet);
    }

    @Override
    public Planet read(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID планети не може бути порожнім");
        }
        
        return planetDao.findById(id);
    }

    @Override
    public void update(Planet planet) {
        validatePlanet(planet);
        
        Planet existingPlanet = planetDao.findById(planet.getId());
        if (existingPlanet == null) {
            throw new IllegalArgumentException("Планета з ID " + planet.getId() + " не знайдена");
        }
        
        planetDao.update(planet);
    }

    @Override
    public void delete(Planet planet) {
        if (planet == null || planet.getId() == null) {
            throw new IllegalArgumentException("Неможливо видалити планету з некоректними даними");
        }
        
        planetDao.delete(planet);
    }

    @Override
    public List<Planet> listAll() {
        return planetDao.findAll();
    }
    
    // Приватний метод для валідації бізнес-правил
    private void validatePlanet(Planet planet) {
        if (planet.getId() == null || planet.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID планети не може бути порожнім");
        }
        
        if (!planet.getId().matches("^[A-Z0-9]+$")) {
            throw new IllegalArgumentException("ID планети повинен містити тільки великі латинські літери та цифри");
        }
        
        if (planet.getName() == null || planet.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Назва планети не може бути порожньою");
        }
        
        if (planet.getName().length() > 500) {
            throw new IllegalArgumentException("Назва планети не повинна перевищувати 500 символів");
        }
    }
}
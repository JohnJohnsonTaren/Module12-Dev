package serviceCRUD;

import dao.ClientDao;
import entitiesHibernate.Client;
import java.util.List;

public class ClientCrudService implements ClientService {
    private final ClientDao clientDao;

    public ClientCrudService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void create(Client client) {
        // Бізнес-логіка валідації
        validateClientName(client);
        
        clientDao.save(client);
    }

    @Override
    public Client read(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID клієнта повинен бути позитивним числом");
        }
        
        return clientDao.findById(id);
    }

    @Override
    public void update(Client client) {
        validateClientName(client);
        
        Client existingClient = clientDao.findById(client.getId());
        if (existingClient == null) {
            throw new IllegalArgumentException("Клієнт з ID " + client.getId() + " не знайдений");
        }
        
        clientDao.update(client);
    }

    @Override
    public void delete(Client client) {
        if (client == null || client.getId() == null) {
            throw new IllegalArgumentException("Неможливо видалити клієнта з некоректними даними");
        }
        
        clientDao.delete(client);
    }

    @Override
    public List<Client> listAll() {
        return clientDao.findAll();
    }

    private void validateClientName(Client client) {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім");
        }
        
        if (client.getName().length() < 3 || client.getName().length() > 200) {
            throw new IllegalArgumentException("Ім'я клієнта повинно містити від 3 до 200 символів");
        }
    }
}
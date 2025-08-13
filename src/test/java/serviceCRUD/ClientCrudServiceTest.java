package serviceCRUD;

import dao.ClientDao;
import dao.ClientDaoImpl;
import entitiesHibernate.Client;
import utilPackage.HibernateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    private ClientCrudService clientService;
    private HibernateUtil hibernateUtil;
    private Client testClient;

    @BeforeEach
    void setUp() {
        hibernateUtil = new HibernateUtil();
        ClientDao ClientDao = new ClientDaoImpl(hibernateUtil);
        clientService = new ClientCrudService(ClientDao);
        
        // Створюємо тестового клієнта для використання в тестах
        testClient = new Client();
        testClient.setName("Test Client");
        clientService.create(testClient);
    }

    @Test
    void create() {
        // Перевіряємо створення нового клієнта
        Client newClient = new Client();
        newClient.setName("New Test Client");
        clientService.create(newClient);
        
        assertNotNull(newClient.getId());
        assertTrue(newClient.getId() > 0);
    }

    @Test
    void read() {
        // Перевіряємо читання існуючого клієнта
        Client readClient = clientService.read(testClient.getId());
        
        assertNotNull(readClient);
        assertEquals("Test Client", readClient.getName());
        assertEquals(testClient.getId(), readClient.getId());
    }

    @Test
    void update() {
        // Перевіряємо оновлення клієнта
        testClient.setName("Updated Client");
        clientService.update(testClient);
        
        Client updatedClient = clientService.read(testClient.getId());
        assertEquals("Updated Client", updatedClient.getName());
    }

    @Test
    void delete() {
        // Перевіряємо видалення клієнта
        clientService.delete(testClient);
        
        Client deletedClient = clientService.read(testClient.getId());
        assertNull(deletedClient);
    }
}
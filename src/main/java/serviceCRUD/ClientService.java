package serviceCRUD;

import entitiesHibernate.Client;

import java.util.List;

public interface ClientService {
    void create(Client client);

    Client read(Long id);

    void update(Client client);

    void delete(Client client);

    List<Client> listAll();
}

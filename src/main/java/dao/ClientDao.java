package dao;

import entitiesHibernate.Client;

import java.util.List;

public interface ClientDao {
    void save(Client client);

    Client findById(Long id);

    void update(Client client);

    void delete(Client client);

    List<Client> findAll();
}

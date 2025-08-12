//  Завдання №3 - створи CRUD сервіси для Client та Planet
//      Опиши сутності Client та Planet.
//      Пропиши Hibernate мапінги для цих сутностей (таблиці в БД client та planet відповідно).
//      Напиши CRUD сервіси для обох сутностей - ClientCrudService та PlanetCrudService.
//      Напиши тестовий код, який додаватиме/видалятиме записи за допомогою цих сервісів.
//          Переконайсь, що всі CRUD операції працюють правильно.
//

package entitiesHibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    @NotNull
    @Size(min = 3, max = 200)
    private String name;
}

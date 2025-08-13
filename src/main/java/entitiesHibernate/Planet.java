//  Завдання №3 - створи CRUD сервіси для Client та Planet
//      Опиши сутності Client та Planet.
//      Пропиши Hibernate мапінги для цих сутностей (таблиці в БД client та planet відповідно).
//      Напиши CRUD сервіси для обох сутностей - ClientCrudService та PlanetCrudService.
//      Напиши тестовий код, який додаватиме/видалятиме записи за допомогою цих сервісів.
//          Переконайсь, що всі CRUD операції працюють правильно.
//

package entitiesHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(length = 50)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Incorrect format")
    private String id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;
}

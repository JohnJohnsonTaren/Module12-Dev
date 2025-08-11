--Завдання №2 - напиши міграції для створення та наповнення БД
--      Напиши дві міграції.
--        Перша міграція (назви її V1__create_db.sql) має створити структуру БД -
--              всі таблиці та зв'язки між ними.
--          Зверни увагу на коректні обмеження.
--          Наприклад, для таблиці ticket поле client_id має бути як foreign key для таблиці
--              client по полю id.

--Відповідно, є наступні сутності з наступними зв'язками:
--      Client (клієнт) - клієнт компанії. Має наступні властивості:
--          id - ідентифікатор, первинний сурогатний ключ, автоінкрементне число.
--          name - ім'я, від 3 до 200 символів включно
--      Planet (планета). Початковий або кінцевий пункт відправлення. Має наступні властивості:
--          id - ідентифікатор планети.
--              Рядок, що складається виключно з латинських букв у верхньому регістрі та цифр. Наприклад, MARS, VEN
--          name - назва планети, рядок від 1 до 500 символів включно
--      Ticket (квиток). Має наступні властивості:
--          id - ідентифікатор квитка, первинний сурогатний ключ, автоінкрементне число.
--          created_at - TIMESTAMP в UTC, коли був створений цей квиток
--          client_id - ідентифікатор клієнта, якому належить цей квиток.
--          from_planet_id - ідентифікатор планети, звідки відправляється пасажир
--          to_planet_id - ідентифікатор планети, куди летить пасажир

CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) NOT NULL CHECK (LENGTH(name) BETWEEN 3 AND 200)
);

CREATE TABLE planet (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR (500) NOT NULL CHECK (LENGTH(NAME) BETWEEN 1 AND 500)
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    client_id BEGIN NOT NULL,
    from_planet_id VARCHAR (50) NOT NULL,
    to_planet_id VARCHAR (50) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (from_planet_id) REFERENCES planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);
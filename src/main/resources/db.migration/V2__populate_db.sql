--  Завдання №2 - напиши міграції для створення та наповнення БД
--      Напиши дві міграції.
--      Друга міграція має наповнити БД даними (назви цю міграцію V2__populate_db.sql).
--          Створи як мінімум:
--              10 клієнтів
--              5 планет
--              10 квитків
--      Переконайсь, що програма запускається і обидві міграції коректно виконуються.

INSERT INTO planet (id, name) VALUES
    ('MERCURY', 'Mercury'),
    ('VENUS',   'Venus'),
    ('EARTH',   'Earth'),
    ('MARS',    'Mars'),
    ('JUPITER', 'Jupiter'),
    ('SATURN',  'Saturn'),
    ('URANUS',  'Uranus'),
    ('NEPTUNE', 'Neptune');

INSERT INTO client (name) VALUES
     ('John Smith'),
     ('Maria Garcia'),
     ('David Brown'),
     ('Emma Wilson'),
     ('Michael Johnson'),
     ('Sarah Davis'),
     ('James Miller'),
     ('Patricia Moore'),
     ('Robert Taylor'),
     ('Jennifer Anderson');

INSERT INTO ticket(created_at, client_id, from_planet_id, to_planet_id) VALUES
    (CURRENT_TIMESTAMP, 1 , 'MERCURY',  'SATURN'),
    (CURRENT_TIMESTAMP, 2 , 'EARTH',    'URANUS'),
    (CURRENT_TIMESTAMP, 3 , 'VENUS',    'JUPITER'),
    (CURRENT_TIMESTAMP, 4 , 'NEPTUNE',  'VENUS'),
    (CURRENT_TIMESTAMP, 5 , 'MARS',     'MERCURY'),
    (CURRENT_TIMESTAMP, 6 , 'JUPITER',  'SATURN'),
    (CURRENT_TIMESTAMP, 7 , 'URANUS',   'NEPTUNE'),
    (CURRENT_TIMESTAMP, 8 , 'NEPTUNE',  'EARTH'),
    (CURRENT_TIMESTAMP, 9 , 'VENUS',    'URANUS'),
    (CURRENT_TIMESTAMP, 10, 'EARTH',    'MARS');

INSERT INTO DEPARTMENTS (NAME)
VALUES
    ('department1'),
    ('department2'),
    ('department3'),
    ('department4'),
    ('department5');

INSERT INTO WORKERS (NAME, DEGREE, SALARY, TYPE)
VALUES
    ('worker1', 'ASSISTANT', 2000.00, 'LECTOR'),
    ('worker2', 'ASSOCIATE_PROFESSOR', 3453.23, 'LECTOR'),
    ('worker3', 'ASSOCIATE_PROFESSOR', 3453.23, 'LECTOR'),
    ('worker4', 'PROFESSOR', 5000.00, 'LECTOR'),
    ('worker5', 'PROFESSOR', 5000.00, 'LECTOR');


INSERT INTO DEPARTMENTS_WORKERS (WORKER_ID, DEPARTMENT_ID)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 1),
    (1, 3),
    (2, 4),
    (3, 5),
    (4, 1),
    (5, 2),
    (1, 4);

INSERT INTO DEPARTMENTS_WORKERS (WORKER_ID, DEPARTMENT_ID)
VALUES (6, 1), (7, 2), (8, 3), (9, 4), (10, 5)

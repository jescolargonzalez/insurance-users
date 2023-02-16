
INSERT INTO aseguradora.users (nombre, apellidos, dni, mail, pass, phone, pais, ciudad, address, iban, birthdate)
VALUES ('superuser', 'superuser', '52896881-D', 'guillem.serra.calahorra@gmail.com', '$12$kTxd0.l1s2TnF5uC3B75HeUSD1Snlpl1/v7cQNZCBy6/JgTQlipkO',
        '666666666', 'ESP', 'mdz', 'calle falsa 123', 'es2312341', '1990-02-08');


INSERT INTO aseguradora.users_roles (user_id, rol_id) VALUES (1, 1);
INSERT INTO aseguradora.users_roles (user_id, rol_id) VALUES (1, 2);
INSERT INTO aseguradora.users_roles (user_id, rol_id) VALUES (1, 3);


INSERT INTO `aseguradora`.`vehicles` (`matricula`, `marca`, `modelo`, `fecha_matriculacion`, `kilometros`, `user_id`)
VALUES ('9579-JRX', 'Peugeot', '508', '2016-01-01', 200000, 1);
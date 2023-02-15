CREATE TABLE `users`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `nombre`      varchar(45)   NOT NULL,
    `apellidos`   varchar(45)   NOT NULL,
    `dni`         varchar(10)   NOT NULL,
    `mail`        varchar(255)  NOT NULL,
    `pass`        varchar(1000) NOT NULL,
    `phone`       varchar(15)   NOT NULL,
    `pais`        varchar(45)   NOT NULL,
    `ciudad`      varchar(45)   NOT NULL,
    `address`     varchar(255)  NOT NULL,
    `iban`        varchar(45)   NOT NULL,
    `birthdate`   date                   DEFAULT NULL,
    `picture`     blob                   DEFAULT NULL,
    `create_time` timestamp     NOT NULL DEFAULT current_timestamp(),
    `update_time` timestamp     NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp (),
    `deleted`     tinyint(4) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_users_dni` (`dni`),
    UNIQUE KEY `idx_users_mail` (`mail`),
    UNIQUE KEY `idx_users_iban` (`iban`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;


INSERT INTO aseguradora.users ( nombre
                              , apellidos, dni, mail, pass, phone, pais, ciudad, address, iban, birthdate)
values ('superuser', 'superuser', '52896881-D', 'guillem.serra.calahorra@gmail.com',
        '$12$kTxd0.l1s2TnF5uC3B75HeUSD1Snlpl1/v7cQNZCBy6/JgTQlipkO', '666666666', 'ESP', 'mdz', 'calle falsa 123',
        'es2312341', '1990-02-08');


CREATE TABLE IF NOT EXISTS aseguradora.roles
(
    id
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    nombre
    VARCHAR
(
    45
) NOT NULL,
    PRIMARY KEY
(
    id
)
    );

INSERT INTO aseguradora.roles(nombre)
VALUES ('operador');
INSERT INTO aseguradora.roles(nombre)
VALUES ('cliente');
INSERT INTO aseguradora.roles(nombre)
VALUES ('administrador');

CREATE TABLE IF NOT EXISTS aseguradora.users_roles
(
    user_id
    INT
    NOT
    NULL,
    rol_id
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    user_id
) REFERENCES users
(
    id
),
    FOREIGN KEY
(
    rol_id
) REFERENCES roles
(
    id
)
    );

insert into aseguradora.users_roles (user_id, rol_id)
values (1, 1);
insert into aseguradora.users_roles (user_id, rol_id)
values (1, 2);
insert into aseguradora.users_roles (user_id, rol_id)
values (1, 3);


CREATE TABLE `vehicles`
(
    `id`                  int(11) NOT NULL AUTO_INCREMENT,
    `matricula`           varchar(45) NOT NULL,
    `marca`               varchar(45) NOT NULL,
    `modelo`              varchar(45) NOT NULL,
    `fecha_matriculacion` date        NOT NULL,
    `kilometros`          int(11) NOT NULL,
    `create_time`         timestamp   NOT NULL DEFAULT current_timestamp(),
    `update_time`         timestamp   NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp (),
    `deleted`             tinyint(4) GENERATED ALWAYS AS (0) VIRTUAL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;



INSERT INTO `aseguradora`.`vehicles`
(`matricula`,
 `marca`,
 `modelo`,
 `fecha_matriculacion`,
 `kilometros`)
VALUES ('9579-JRX',
        'Peugeot',
        '508',
        '2016-01-01',
        200000);



CREATE TABLE `users_vehicles`
(
    `user_id`    int(11) NOT NULL,
    `vehicle_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`, `vehicle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `aseguradora`.`users_vehicles`
(`user_id`,
 `vehicle_id`)
VALUES (11,
        1);

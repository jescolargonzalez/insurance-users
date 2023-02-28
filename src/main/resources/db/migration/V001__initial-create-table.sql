CREATE TABLE `users` (
    `id`          INT(11) NOT NULL auto_increment,
    `nombre`      VARCHAR(45)   NOT NULL,
    `apellidos`   VARCHAR(45)   NOT NULL,
    `dni`         VARCHAR(10)   NOT NULL,
    `mail`        VARCHAR(255)  NOT NULL,
    `pass`        VARCHAR(1000) NOT NULL,
    `phone`       VARCHAR(15)   NOT NULL,
    `pais`        VARCHAR(45)   NOT NULL,
    `ciudad`      VARCHAR(45)   NOT NULL,
    `address`     VARCHAR(255)  NOT NULL,
    `iban`        VARCHAR(45)   NOT NULL,
    `birthdate`   DATE                   DEFAULT NULL,
    `picture`     BLOB                   DEFAULT NULL,
    `create_time` TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `update_time` TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP() on
        UPDATE CURRENT_TIMESTAMP (),
    `deleted`     TINYINT(4) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_users_dni` (`dni`),
    UNIQUE KEY `idx_users_mail` (`mail`),
    UNIQUE KEY `idx_users_iban` (`iban`)
) engine = innodb auto_increment = 1 DEFAULT charset = utf8mb4;

CREATE TABLE IF NOT EXISTS roles (
    id        INT         NOT NULL auto_increment,
    nombre    VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id    INT    NOT NULL,
    rol_id     INT    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (rol_id) REFERENCES roles (id)
);

CREATE TABLE `vehicles`
(
    `id`                  INT(11) NOT NULL auto_increment,
    `matricula`           VARCHAR(45) NOT NULL,
    `marca`               VARCHAR(45) NOT NULL,
    `modelo`              VARCHAR(45) NOT NULL,
    `fecha_matriculacion` DATE        NOT NULL,
    `kilometros`          INT(11) NOT NULL,
    `create_time`         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `update_time`         TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP() on UPDATE CURRENT_TIMESTAMP (),
    `deleted`             TINYINT(4) NOT NULL DEFAULT 0,
    `user_id`             INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) engine = innodb auto_increment = 1 DEFAULT charset = utf8mb4;


/*INSERTS*/


INSERT INTO roles (nombre) VALUES ('operador');
INSERT INTO roles (nombre) VALUES ('cliente');
INSERT INTO roles (nombre) VALUES ('administrador');


INSERT INTO users (nombre, apellidos, dni, mail, pass, phone, pais, ciudad, address, iban, birthdate)
VALUES ('superuser', 'superuser', '52896881-D', 'guillem.serra.calahorra@gmail.com', '$2a$12$gMwHKu9E2rPC9BmsIxXmjOwHd5rA4eihzBA1P9qCShFsO/GKkZTjG',
        '666666666', 'ESP', 'mdz', 'calle falsa 123', 'es2312341', '1990-02-08');


INSERT INTO users_roles (user_id, rol_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, rol_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, rol_id) VALUES (1, 3);


INSERT INTO vehicles (matricula, marca, modelo, fecha_matriculacion, kilometros, user_id)
VALUES ('9579-JRX', 'Peugeot', '508', '2016-01-01', 200000, 1);
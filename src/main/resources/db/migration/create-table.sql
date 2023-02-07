CREATE TABLE aseguradora.users
(
    id        INT           NOT NULL AUTO_INCREMENT,
    nombre    VARCHAR(45)   NOT NULL,
    apellidos VARCHAR(45)   NOT NULL,
    dni       VARCHAR(45)   NOT NULL,
    mail      VARCHAR(45)   NOT NULL,
    pass      VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO aseguradora.users ( nombre
                              , apellidos, dni, mail, pass)
values ('superuser', 'superuser', '52896881-D', 'guillem.serra.calahorra@gmail.com', 'chicochicochico');

CREATE TABLE aseguradora.roles
(
    id     INT         NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO aseguradora.roles(nombre)
VALUES ('operador');
INSERT INTO aseguradora.roles(nombre)
VALUES ('cliente');
INSERT INTO aseguradora.roles(nombre)
VALUES ('administrador');

CREATE TABLE aseguradora.users_roles
(
    user_id INT NOT NULL,
    rol_id  INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (rol_id) REFERENCES roles (id)
);

insert into aseguradora.users_roles (user_id, rol_id)
values (1, 1);
insert into aseguradora.users_roles (user_id, rol_id)
values (1, 2);
insert into aseguradora.users_roles (user_id, rol_id)
values (1, 3);




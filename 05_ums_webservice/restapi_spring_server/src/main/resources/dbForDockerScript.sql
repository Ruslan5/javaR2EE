drop all objects;
CREATE TABLE ROLES_DB
(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);
INSERT INTO ROLES_DB (id, name) VALUES(1, 'admin');
INSERT INTO ROLES_DB (id, name) VALUES(2, 'user');

CREATE TABLE USERS_DB(
                         id bigint auto_increment primary key,
                         login VARCHAR(255) NOT NULL,
                         password VARCHAR(255)  NOT NULL,
                         email VARCHAR(255),
                         firstname VARCHAR(255),
                         lastname VARCHAR(255),
                         birthday DATE,
                         role_id INTEGER,
                         FOREIGN KEY (role_id) REFERENCES ROLES_DB (id) on delete cascade on update cascade
);

INSERT INTO USERS_DB (login, password, email, firstname, lastname, birthday, role_id) VALUES('login1', '$2a$12$UHhKVP7a56oZEWJL04ZMGe9LAVRctxVWzLekER1RGNTE1t4jWOwwC', 'email1', 'fname1', 'lname1', '2000-11-01', 1);
INSERT INTO USERS_DB (login, password, email, firstname, lastname, birthday, role_id) VALUES('login2', '$2a$12$jKgsnNzM1j.hi4Jv9b29WessFd0uG0NdsyJ0QpQhMGHrd87qwpmv.', 'email2', 'fname2', 'lname2', '2000-11-01', 2);
--drop all objects;

CREATE TABLE ROLES_DB
(
 ID bigint PRIMARY KEY AUTO_INCREMENT,
 NAME VARCHAR(255)
 );
INSERT INTO ROLES_DB VALUES(1, 'admin');
INSERT INTO ROLES_DB VALUES(2, 'user');

CREATE TABLE USERS_DB(
 ID bigint auto_increment primary key,
 LOGIN VARCHAR(255) NOT NULL,
 PASSWORD VARCHAR(255)  NOT NULL,
 EMAIL VARCHAR(255),
 FIRSTNAME VARCHAR(255),
 LASTNAME VARCHAR(255),
 BIRTHDAY DATE,
 ROLE_ID INTEGER,
   FOREIGN KEY (ROLE_ID) REFERENCES ROLES_DB (ID) on delete cascade on update cascade
);

INSERT INTO USERS_DB VALUES(default, 'login1', 'pass1', 'email1', 'fname1', 'lname1', '2000-11-01', 1);
INSERT INTO USERS_DB VALUES(default, 'login2', 'pass2', 'email2', 'fname2', 'lname2', '2000-11-01', 2);
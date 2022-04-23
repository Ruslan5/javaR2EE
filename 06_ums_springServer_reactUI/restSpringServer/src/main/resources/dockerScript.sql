INSERT INTO ROLES_DB(name) VALUES ('admin');
INSERT INTO ROLES_DB(name) VALUES ('user');
INSERT INTO USERS_DB(login, password, email, firstname, lastname, birthday, role_id) VALUES ('login1', '$2a$12$UHhKVP7a56oZEWJL04ZMGe9LAVRctxVWzLekER1RGNTE1t4jWOwwC', 'email1', 'fname1', 'lname1', '2000-11-01', 1);
INSERT INTO USERS_DB(login, password, email, firstname, lastname, birthday, role_id) VALUES('login2', '$2a$12$jKgsnNzM1j.hi4Jv9b29WessFd0uG0NdsyJ0QpQhMGHrd87qwpmv.', 'email2', 'fname2', 'lname2', '2000-11-01', 2);
-- pass1
-- pass2
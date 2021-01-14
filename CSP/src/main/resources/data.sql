--delete from role;

INSERT INTO role (role_id, role) VALUES (1,'Admin') on conflict (role_id) do nothing ;
INSERT INTO role (role_id, role) VALUES (2,'User') on conflict (role_id) do nothing ;

-- delete from users where upper(username)= upper('Admin');

INSERT INTO users (user_id, date, email, first_name, last_name, password, username)
VALUES (0,CURRENT_TIMESTAMP,'admin@gmail.com','admin',
        'admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','admin') on conflict (user_id) do nothing ;

INSERT INTO users (user_id, date, email, first_name, last_name, password, username)
VALUES (1,CURRENT_TIMESTAMP,'genericuser1@gmail.com','genericuser1',
        'genericuser1','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','genericuser1') on conflict (user_id) do nothing ;

INSERT INTO users (user_id, date, email, first_name, last_name, password, username)
VALUES (2,CURRENT_TIMESTAMP,'genericuser2@gmail.com','genericuser2',
        'genericuser2','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','genericuser2') on conflict (user_id) do nothing ;

-- delete from user_role where user_id = 0;
insert into user_role (user_id,role_id) values(0,1);
insert into user_role (user_id,role_id) values(1,2);
insert into user_role (user_id,role_id) values(2,2);
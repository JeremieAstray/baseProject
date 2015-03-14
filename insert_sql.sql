
INSERT INTO baseproject.t_user (name, password, id) VALUES ('jessy', '6b9611669f31f2a9a2c9e80d185b127e', 23);
INSERT INTO baseproject.t_user (name, password, id) VALUES ('superadmin', '698d51a19d8a121ce581499d7b701668', 24);

INSERT INTO baseproject.t_role (role_name, id) VALUES ('superadmin', 17);
INSERT INTO baseproject.t_role (role_name, id) VALUES ('admin', 18);

INSERT INTO baseproject.t_role_user (role_id, user_id) VALUES (18, 23);
INSERT INTO baseproject.t_role_user (role_id, user_id) VALUES (17, 24);

INSERT INTO baseproject.t_permission (permission_name, id) VALUES ('user:create', 22);
INSERT INTO baseproject.t_permission (permission_name, id) VALUES ('user:update', 23);
INSERT INTO baseproject.t_permission (permission_name, id) VALUES ('menu:create', 24);
INSERT INTO baseproject.t_permission (permission_name, id) VALUES ('user:del', 25);

INSERT INTO baseproject.t_role_permission (role_id, permission_id) VALUES (17, 22);
INSERT INTO baseproject.t_role_permission (role_id, permission_id) VALUES (17, 23);
INSERT INTO baseproject.t_role_permission (role_id, permission_id) VALUES (17, 24);
INSERT INTO baseproject.t_role_permission (role_id, permission_id) VALUES (18, 22);
INSERT INTO baseproject.t_role_permission (role_id, permission_id) VALUES (18, 23);
INSERT INTO baseproject.t_role_permission (role_id, permission_id) VALUES (17, 25);
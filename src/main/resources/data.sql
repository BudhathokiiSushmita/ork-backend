-- File name has to be data.sql, otherwise wont work
--
INSERT IGNORE INTO user_entity (id, username, password, email, contact_number, created)
VALUES
       (1, 'admin', '$2a$12$g13jVWTXy6GAAzVPd3/3MuJqxCcTGbeg3NOyQR/wFg.RUSw0ADNkO', 'ork.admin@ork.com', '1234567899', '2024-08-28 07:28:30');
-- Admin@123456

INSERT IGNORE INTO user_roles (user_id, role_id)
VALUES
       (1, 1);

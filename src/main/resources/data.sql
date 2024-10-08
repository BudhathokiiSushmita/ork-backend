-- File name has to be data.sql, otherwise wont work
--
INSERT IGNORE INTO ork_user (id, username, password, email, contact_number, created, role_id)
VALUES
       (1, 'admin', '$2a$12$g13jVWTXy6GAAzVPd3/3MuJqxCcTGbeg3NOyQR/wFg.RUSw0ADNkO', 'ork.admin@ork.com', '1234567899', '2024-08-28 07:28:30', 1);
-- Admin@123456

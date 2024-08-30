-- File name has to be data.sql, otherwise wont work

INSERT IGNORE INTO user (id, username, password, ork_role_id, email, contact_number, created)
VALUES
       (1, 'admin', '$2a$12$g13jVWTXy6GAAzVPd3/3MuJqxCcTGbeg3NOyQR/wFg.RUSw0ADNkO', 1, 'ork.admin@ork.com', '1234567899', '2024-08-28 07:28:30');
-- Admin@123456

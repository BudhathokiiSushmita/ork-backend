
INSERT IGNORE INTO ork_role_nav_permission (ork_role_id, nav_permission_id)
VALUES
--        admin panel
       (1, 1),
       (1, 3),
       (1, 5),
       (1, 6),
       (1, 7),

--        recruiter panel
        (2, 4),
        (2, 5),
        (2, 6),
        (2, 7),

--        hr panel
       (3, 6),
       (3, 7),

--        director panel
       (4, 6),
       (4, 7),

--        applicant panel
       (5, 2),
       (5, 4),
       (5, 7);

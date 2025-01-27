INSERT INTO roles (id, name) VALUES (1, 'Admin');
INSERT INTO roles (id, name) VALUES (2, 'Teacher');
INSERT INTO roles (id, name) VALUES (3, 'Student');

INSERT INTO users (id, username, password, email, role_id) VALUES
                                                              (1, 'admin', 'admin123', 'admin@example.com', 1),
                                                              (2, 'teacher', 'teacher123', 'teacher@example.com', 2),
                                                              (3, 'student', 'student123', 'student@example.com', 3);

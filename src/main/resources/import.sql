-- INSERT USERS
INSERT INTO tbl_users (name, email, username, password) VALUES ('Usuario1', 'usuario1@example.com', 'user1', 'password1');
INSERT INTO tbl_users (name, email, username, password) VALUES ('Usuario2', 'usuario2@example.com', 'user2', 'password2');
INSERT INTO tbl_users (name, email, username, password) VALUES ('Usuario3', 'usuario3@example.com', 'user3', 'password3');

-- INSERT POSTS
INSERT INTO tbl_posts (title, content, created_at, user_id) VALUES ('Título del Post 1', 'Contenido del Post 1', NOW(), 1);
INSERT INTO tbl_posts (title, content, created_at, user_id) VALUES ('Título del Post 2', 'Contenido del Post 2', NOW(), 2);
INSERT INTO tbl_posts (title, content, created_at, user_id) VALUES ('Título del Post 3', 'Contenido del Post 3', NOW(), 3);

-- INSERT COMMENTS
INSERT INTO tbl_comments (content, created_at, user_id, post_id) VALUES ('Comentario 1 para Post 1', NOW(), 1, 1);
INSERT INTO tbl_comments (content, created_at, user_id, post_id) VALUES ('Comentario 2 para Post 1', NOW(), 2, 1);
INSERT INTO tbl_comments (content, created_at, user_id, post_id) VALUES ('Comentario 1 para Post 2', NOW(), 3, 2);
INSERT INTO tbl_comments (content, created_at, user_id, post_id) VALUES ('Comentario 2 para Post 2', NOW(), 1, 2);
INSERT INTO tbl_comments (content, created_at, user_id, post_id) VALUES ('Comentario 1 para Post 3', NOW(), 2, 3);
INSERT INTO tbl_comments (content, created_at, user_id, post_id) VALUES ('Comentario 2 para Post 3', NOW(), 3, 3);
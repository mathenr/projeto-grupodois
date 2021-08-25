CREATE DATABASE plataforma_cursos_db;
CREATE USER 'plataformacursos'@'%' IDENTIFIED BY 'plataforma%';
GRANT ALL PRIVILEGES ON *.* to `plataformacursos`@`%`;`
FLUSH PRIVILEGES;
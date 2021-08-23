CREATE DATABASE plataforma_cursos_db;
CREATE USER 'plataformacursos'@'%' IDENTIFIED BY 'plataforma%';
GRANT SELECT ON *.* TO 'plataformacursos'@'%';
FLUSH PRIVILEGES;
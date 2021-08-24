CREATE DATABASE plataforma_cursos_db;
CREATE USER 'plataformacursos'@'%' IDENTIFIED BY 'plataforma%';
GRANT ALL PRIVILEGES ON *.* TO 'plataformacursos'@'%';
FLUSH PRIVILEGES;
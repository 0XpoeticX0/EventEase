-- Show databases;
-- create database event_ease_db;
-- USE event_ease_db;
-- Show tables;

-- CREATE TABLE users (
--     u_id INT AUTO_INCREMENT PRIMARY KEY,
--     firstname VARCHAR(100) NOT NULL,
--     lastname VARCHAR(100) NOT NULL,
--     email VARCHAR(255) UNIQUE NOT NULL,
--     mobileNumber VARCHAR(15),
--     password VARCHAR(255) NOT NULL, 
--     age INT CHECK (age >= 18),
--     role ENUM('admin', 'user') NOT NULL default('user'), 
--     status ENUM('active', 'inactive') NOT NULL default('active'),
--     image varchar(255) 
-- );

-- CREATE TABLE events (
--     e_id INT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     description TEXT,
--     location VARCHAR(255) NOT NULL,
--     price INT CHECK (price >= 0), 
--     image varchar(255) 
-- );


-- select * from users;
-- select * from events;

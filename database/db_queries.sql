CREATE DATABASE IF NOT EXISTS health_tracking;
USE health_tracking;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT,
    gender VARCHAR(10),
    height DOUBLE,
    current_weight DOUBLE,
    UNIQUE (username),
    UNIQUE (email)
    );

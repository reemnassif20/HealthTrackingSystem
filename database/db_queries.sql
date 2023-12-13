DROP DATABASE IF EXISTS health_tracking;
CREATE DATABASE health_tracking;
USE health_tracking;
CREATE TABLE User (
                      user_id INT PRIMARY KEY,
                      username VARCHAR(255) UNIQUE,
                      password VARCHAR(255),
                      full_name VARCHAR(255),
                      email VARCHAR(255),
                      gender VARCHAR(10),
                      current_weight DECIMAL(5,2),
                      age INT,
                      height INT
);

CREATE TABLE Food (
                      food_id INT PRIMARY KEY,
                      food_name VARCHAR(255),
                      food_type VARCHAR(255),
                      calories_per_hundred_units INT
);

CREATE TABLE Reminder (
                          reminder_id INT,
                          user_id INT,
                          reminder_message VARCHAR(255),
                          reminder_period INT,
                          PRIMARY KEY (reminder_id, user_id),
                          FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Exercise (
                          exercise_id INT PRIMARY KEY,
                          exercise_type VARCHAR(255),
                          exercise_name VARCHAR(255),
                          activity_type_calories INT
);

CREATE TABLE History (
                         history_id INT,
                         history_date DATE,
                         history_weight DECIMAL(5,2),
                         user_id INT,
                         PRIMARY KEY (history_id, user_id),
                         FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE UserExercises (
                               user_id INT,
                               exercise_id INT,
                               exercise_date DATE,
                               duration INT,
                               PRIMARY KEY (user_id, exercise_id, exercise_date),
                               FOREIGN KEY (user_id) REFERENCES User(user_id),
                               FOREIGN KEY (exercise_id) REFERENCES Exercise(exercise_id)
);

CREATE TABLE UserFood (
                          user_id INT,
                          food_id INT,
                          meal_type VARCHAR(255),
                          quantity INT,
                          food_date DATE,
                          PRIMARY KEY (user_id, food_id, food_date),
                          FOREIGN KEY (user_id) REFERENCES User(user_id),
                          FOREIGN KEY (food_id) REFERENCES Food(food_id)
);

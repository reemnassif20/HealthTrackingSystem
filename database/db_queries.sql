DROP DATABASE IF EXISTS health_tracking;
CREATE DATABASE health_tracking;
USE health_tracking;

CREATE TABLE `user` (
                        `user_id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `full_name` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `gender` varchar(10) DEFAULT NULL,
                        `current_weight` decimal(5,2) DEFAULT NULL,
                        `age` int(11) DEFAULT NULL,
                        `height` int(11) DEFAULT NULL,
                        `registration_date` date DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `email` (`email`)
);
CREATE TABLE `exercise` (
                        `exercise_id` int(11) NOT NULL AUTO_INCREMENT,
                        `exercise_type` varchar(255) DEFAULT NULL,
                        `exercise_name` varchar(255) DEFAULT NULL,
                        `activity_type_calories` int(11) DEFAULT NULL,
                        PRIMARY KEY (`exercise_id`)
                    );

CREATE TABLE `food` (
                          `food_id` int(11) NOT NULL AUTO_INCREMENT,
                          `food_name` varchar(255) DEFAULT NULL,
                          `food_type` varchar(255) DEFAULT NULL,
                          `calories_per_hundred_units` int(11) DEFAULT NULL,
                           PRIMARY KEY (`food_id`)
);

CREATE TABLE `history` (
                          `history_id` int(11) NOT NULL AUTO_INCREMENT,
                          `history_date` date DEFAULT NULL,
                          `history_weight` decimal(5,2) DEFAULT NULL,
                          `user_id` int(11) NOT NULL,
                           PRIMARY KEY (`history_id`,`user_id`),
                           KEY `user_id` (`user_id`),
                           CONSTRAINT `history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `reminder` (
                            `reminder_id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` int(11) NOT NULL,
                            `reminder_message` varchar(255) DEFAULT NULL,
                            `reminder_period` int(11) DEFAULT NULL,
                            PRIMARY KEY (`reminder_id`,`user_id`),
                            KEY `user_id` (`user_id`),
                            CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);



CREATE TABLE `userexercises` (
                            `user_id` int(11) NOT NULL,
                            `exercise_id` int(11) NOT NULL,
                            `exercise_date` date NOT NULL,
                            `duration` int(11) DEFAULT NULL,
                             PRIMARY KEY (`user_id`,`exercise_id`,`exercise_date`),
                             KEY `exercise_id` (`exercise_id`),
                             CONSTRAINT `userexercises_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                             CONSTRAINT `userexercises_ibfk_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`exercise_id`)
);

CREATE TABLE `userfood` (
                            `user_id` int(11) NOT NULL,
                            `food_id` int(11) NOT NULL,
                            `meal_type` varchar(255) DEFAULT NULL,
                            `quantity` int(11) DEFAULT NULL,
                            `food_date` date NOT NULL,
                            PRIMARY KEY (`user_id`,`food_id`,`food_date`),
                            KEY `food_id` (`food_id`),
                            CONSTRAINT `userfood_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                            CONSTRAINT `userfood_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
);
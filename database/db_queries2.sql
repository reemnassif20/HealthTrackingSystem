DROP DATABASE IF EXISTS health_tracking_system;
CREATE DATABASE health_tracking_system;
USE health_tracking_system;

CREATE TABLE `user` (
                        `user_id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
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
                           PRIMARY KEY (`history_id`, `user_id`),
                           KEY `history_ibfk_1` (`user_id`),
                           CONSTRAINT `history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `reminder` (
                            `reminder_id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` int(11) NOT NULL,
                            `reminder_message` varchar(255) DEFAULT NULL,
                            `reminder_period` int(11) DEFAULT NULL,
                            PRIMARY KEY (`reminder_id`, `user_id`),
                            KEY `reminder_ibfk_1` (`user_id`),
                            CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);


CREATE TABLE `userexercises` (
                                 `user_id` int(11) NOT NULL,
                                 `exercise_id` int(11) NOT NULL,
                                 `exercise_date` date NOT NULL,
                                 `duration` int(11) DEFAULT NULL,
                                 PRIMARY KEY (`user_id`,`exercise_id`,`exercise_date`),
                                 KEY `userexercises_ibfk_2` (`exercise_id`),
                                 CONSTRAINT `userexercises_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                                 CONSTRAINT `userexercises_ibfk_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`exercise_id`)
);

CREATE TABLE `userfood` (
                            `userfood_id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` int(11) NOT NULL,
                            `food_id` int(11) NOT NULL,
                            `meal_type` varchar(255) DEFAULT NULL,
                            `quantity` int(11) NOT NULL,
                            `food_date` date NOT NULL,
                            PRIMARY KEY (`userfood_id`),
                            KEY `userfood_ibfk_1` (`user_id`),
                            KEY `userfood_ibfk_2` (`food_id`),
                            CONSTRAINT `userfood_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                            CONSTRAINT `userfood_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
);


INSERT INTO `exercise` (`exercise_type`, `exercise_name`, `activity_type_calories`) VALUES
                                                                                        ( 'General Activity', 'Walking', 5),
                                                                                        ( 'General Activity', 'Swimming', 8),
                                                                                        ( 'General Activity', 'Bicycling', 7),
                                                                                        ( 'General Activity', 'Running', 10),
                                                                                        ( 'General Activity', 'Jumping Jacks', 6),
                                                                                        ( 'General Activity', 'Dancing', 9),
                                                                                        ( 'General Activity', 'Hiking', 8),
                                                                                        ( 'General Activity', 'Cycling', 7),
                                                                                        ( 'General Activity', 'Yoga', 4),
                                                                                        ( 'General Activity', 'Pilates', 6),
                                                                                        ( 'GYM Training', 'Squat', 10),
                                                                                        ( 'GYM Training', 'Blank', 12),
                                                                                        ( 'GYM Training', 'Deadlift', 15),
                                                                                        ( 'GYM Training', 'Bench Press', 13),
                                                                                        ( 'GYM Training', 'Leg Press', 11),
                                                                                        ( 'GYM Training', 'Shoulder Press', 12),
                                                                                        ( 'GYM Training', 'Bicep Curls', 8),
                                                                                        ( 'GYM Training', 'Tricep Dips', 9),
                                                                                        ( 'GYM Training', 'Plank', 7),
                                                                                        ( 'GYM Training', 'Lat Pulldown', 11);

INSERT INTO `food` (`food_name`, `food_type`, `calories_per_hundred_units`) VALUES
                                                                                ( 'Chocolate Cake', 'Dessert', 250),
                                                                                ( 'Cheesecake', 'Dessert', 300),
                                                                                ( 'Brownie', 'Dessert', 200),
                                                                                ( 'Ice Cream Sundae', 'Dessert', 350),
                                                                                ( 'Apple Pie', 'Dessert', 220),
                                                                                ( 'Cupcake', 'Dessert', 180),
                                                                                ( 'Pudding', 'Dessert', 160),
                                                                                ( 'Cookies', 'Dessert', 180),
                                                                                ( 'Tiramisu', 'Dessert', 280),
                                                                                ( 'Fruit Tart', 'Dessert', 150),
                                                                                ( 'Spaghetti Bolognese', 'Food', 400),
                                                                                ( 'Grilled Chicken', 'Food', 300),
                                                                                ( 'Vegetable Stir-Fry', 'Food', 250),
                                                                                ( 'Beef Burger', 'Food', 350),
                                                                                ( 'Salmon Fillet', 'Food', 320),
                                                                                ( 'Vegetarian Pizza', 'Food', 280),
                                                                                ( 'Shrimp Scampi', 'Food', 380),
                                                                                ( 'Tofu Curry', 'Food', 220),
                                                                                ( 'Beef Tacos', 'Food', 300),
                                                                                ( 'Chicken Alfredo', 'Food', 350),
                                                                                ( 'Orange Juice', 'Drink', 50),
                                                                                ( 'Coffee', 'Drink', 5),
                                                                                ( 'Iced Tea', 'Drink', 20),
                                                                                ( 'Mango Smoothie', 'Drink', 120),
                                                                                ( 'Coca-Cola', 'Drink', 140),
                                                                                ( 'Green Tea', 'Drink', 2),
                                                                                ( 'Lemonade', 'Drink', 80),
                                                                                ( 'Milkshake', 'Drink', 200),
                                                                                ( 'Water', 'Drink', 0),
                                                                                ('Peach Iced Tea', 'Drink', 30);

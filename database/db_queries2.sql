DROP DATABASE IF EXISTS health_tracking_system;
CREATE DATABASE health_tracking_system;
USE health_tracking_system;

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
                           PRIMARY KEY (`history_id`, `user_id`),
                           CONSTRAINT `history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `reminder` (
                            `reminder_id` int(11) NOT NULL AUTO_INCREMENT,
                            `user_id` int(11) NOT NULL,
                            `reminder_message` varchar(255) DEFAULT NULL,
                            `reminder_period` int(11) DEFAULT NULL,
                            PRIMARY KEY (`reminder_id`, `user_id`),
                            CONSTRAINT `reminder_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `userexercises` (
                                 `user_id` int(11) NOT NULL,
                                 `exercise_id` int(11) NOT NULL,
                                 `exercise_date` date NOT NULL,
                                 `duration` int(11) DEFAULT NULL,
                                 PRIMARY KEY (`user_id`, `exercise_id`, `exercise_date`),
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
                            CONSTRAINT `userfood_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                            CONSTRAINT `userfood_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`)
);


INSERT INTO `user` (`user_id`, `username`, `password`, `full_name`, `email`, `gender`, `current_weight`, `age`, `height`, `registration_date`) VALUES
                                                                                                                                                   (3, 'reemnassif', '123', 'reemnassif', 'reemnassif', 'Male', 123.00, 21, 222, '2023-12-14'),
                                                                                                                                                   (6, 'reemnassif.com', '123456789', 'Reem', 'reemnassif.com', 'Male', 123.00, 22, 12, '2023-12-14'),
                                                                                                                                                   (7, 'rrrrrr', '1234', 'reeem', 'rrrrrr', 'Male', 21.00, 212, 52, '2023-12-14'),
                                                                                                                                                   (18, 'naass', '123456', 'reem', 'naass', 'Male', 25.00, 24, 157, '2023-12-14'),
                                                                                                                                                   (21, 'reem@kkk', '12345', 'reem', 'reem@kkk', 'Female', 25.00, 122, 156, '2023-12-14'),
                                                                                                                                                   (22, 'reee,@gmail', '123456', 'reem nassif', 'reee,@gmail', 'Female', 25.22, 52, 555, '2023-12-14'),
                                                                                                                                                   (25, 'rrr@gmail.com', '1234567', 'rrr', 'rrr@gmail.com', 'Female', 55.00, 22, 45, '2023-12-14'),
                                                                                                                                                   (27, 'Reem Nassif', '123456', 'reemnassif', '123456', 'Female', 67.50, 22, 169, '2023-12-14'),
                                                                                                                                                   (28, 'reemmmmmm', '123456', 'reem', 'reemmmmmm', 'Female', 25.00, 25, 123, '2023-12-14'),
                                                                                                                                                   (32, 'reemn@gmail', '45', 'reemnassif', 'reemn@gmail', 'Female', 122.00, 55, 25, '2023-12-14'),
                                                                                                                                                   (33, 'reemnassiffff', '12345', 'rrrr', 'reemnassiffff', 'Female', 125.00, 22, 125, '2023-12-14'),
                                                                                                                                                   (34, 'rrrrrrrrrr', '147', 'rrr', 'rrrrrrrrrr', 'Female', 12.00, 25, 123, '2023-12-14'),
                                                                                                                                                   (35, 'myemail.com', '123456', 'reem', 'myemail.com', 'Female', 444.00, 24, 124, '2023-12-14'),
                                                                                                                                                   (36, 'rrrrr', '123456', 'reem', 'rrrrr', 'Female', 12.00, 24, 22, '2023-12-14'),
                                                                                                                                                   (37, '4444', '12345', 'REEEE', '4444', 'Female', 10.00, 22, 10, '2023-12-14'),
                                                                                                                                                   (38, 'reem', '123456789', 'XX', 'reem', 'Female', 222.00, 24, 222, '2023-12-14');


INSERT INTO `food` (`food_id`, `food_name`, `food_type`, `calories_per_hundred_units`) VALUES
                                                                                           (4, 'Chocolate Cake', 'Dessert', 250),
                                                                                           (5, 'Cheesecake', 'Dessert', 300),
                                                                                           (6, 'Brownie', 'Dessert', 200),
                                                                                           (7, 'Ice Cream Sundae', 'Dessert', 350),
                                                                                           (8, 'Apple Pie', 'Dessert', 220),
                                                                                           (9, 'Cupcake', 'Dessert', 180),
                                                                                           (10, 'Pudding', 'Dessert', 160),
                                                                                           (11, 'Cookies', 'Dessert', 180),
                                                                                           (12, 'Tiramisu', 'Dessert', 280),
                                                                                           (13, 'Fruit Tart', 'Dessert', 150),
                                                                                           (14, 'Spaghetti Bolognese', 'Food', 400),
                                                                                           (15, 'Grilled Chicken', 'Food', 300),
                                                                                           (16, 'Vegetable Stir-Fry', 'Food', 250),
                                                                                           (17, 'Beef Burger', 'Food', 350),
                                                                                           (18, 'Salmon Fillet', 'Food', 320),
                                                                                           (19, 'Vegetarian Pizza', 'Food', 280),
                                                                                           (20, 'Shrimp Scampi', 'Food', 380),
                                                                                           (21, 'Tofu Curry', 'Food', 220),
                                                                                           (22, 'Beef Tacos', 'Food', 300),
                                                                                           (23, 'Chicken Alfredo', 'Food', 350),
                                                                                           (24, 'Orange Juice', 'Drink', 50),
                                                                                           (25, 'Coffee', 'Drink', 5),
                                                                                           (26, 'Iced Tea', 'Drink', 20),
                                                                                           (27, 'Mango Smoothie', 'Drink', 120),
                                                                                           (28, 'Coca-Cola', 'Drink', 140),
                                                                                           (29, 'Green Tea', 'Drink', 2),
                                                                                           (30, 'Lemonade', 'Drink', 80),
                                                                                           (31, 'Milkshake', 'Drink', 200),
                                                                                           (32, 'Water', 'Drink', 0),
                                                                                           (33, 'Peach Iced Tea', 'Drink', 30);





INSERT INTO `userfood` (`userfood_id`, `user_id`, `food_id`, `meal_type`, `quantity`, `food_date`) VALUES
                                                                                                       (1, 27, 14, 'Breakfast', 54, '2023-12-17'),
                                                                                                       (2, 27, 14, 'Breakfast', 74, '2023-12-17'),
                                                                                                       (3, 27, 14, 'Breakfast', 74, '2023-12-17'),
                                                                                                       (4, 27, 14, 'Breakfast', 114, '2023-12-17'),
                                                                                                       (5, 27, 17, 'Breakfast', 50, '2023-12-17'),
                                                                                                       (6, 27, 16, 'Breakfast', 9, '2023-12-17'),
                                                                                                       (7, 27, 16, 'Breakfast', 5, '2023-11-29'),
                                                                                                       (8, 27, 23, 'Breakfast', 20, '2023-12-17'),
                                                                                                       (9, 27, 7, 'Breakfast', 50, '2023-12-17'),
                                                                                                       (10, 27, 24, 'Breakfast', 153, '2023-12-17'),
                                                                                                       (11, 27, 23, 'Lunch', 1, '2023-12-17'),
                                                                                                       (12, 27, 4, 'Lunch', 2, '2023-12-17'),
                                                                                                       (13, 27, 25, 'Lunch', 3, '2023-12-17'),
                                                                                                       (14, 27, 22, 'Dinner', 1, '2023-12-17'),
                                                                                                       (15, 27, 10, 'Dinner', 6, '2023-12-17'),
                                                                                                       (16, 27, 24, 'Drink', 8, '2023-12-17'),
                                                                                                       (17, 27, 23, 'Breakfast', 20, '2023-12-03'),
                                                                                                       (18, 27, 4, 'Breakfast', 1, '2023-12-17'),
                                                                                                       (19, 27, 14, 'Lunch', 555, '2023-12-17'),
                                                                                                       (20, 27, 14, 'Dinner', 0, '2023-12-17'),
                                                                                                       (21, 27, 4, 'Dinner', 0, '2023-12-17'),
                                                                                                       (22, 27, 24, 'Dinner', 0, '2023-12-17'),
                                                                                                       (23, 27, 24, 'Lunch', 0, '2023-12-17'),
                                                                                                       (24, 27, 15, 'Breakfast', 20, '2023-12-17');

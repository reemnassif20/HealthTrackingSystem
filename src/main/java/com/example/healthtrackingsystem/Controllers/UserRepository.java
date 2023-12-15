package com.example.healthtrackingsystem.Controllers;

import com.example.healthtrackingsystem.Models.User;

public class UserRepository {
        private static User currentUser;

        public static User getCurrentUser() {
            return currentUser;
        }

        public static void setCurrentUser(User user) {
            currentUser = user;
        }
}


package com.example.healthtrackingsystem.Models;

import java.math.BigDecimal;
import java.util.Date;

public class UserBuilder {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String gender;
    private BigDecimal currentWeight;
    private int age;
    private int height;
    private Date registrationDate;


    public UserBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }


    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder currentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
        return this;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder height(int height) {
        this.height = height;
        return this;
    }
    public UserBuilder registrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
    public User build() {
        return new User(userId, username, password, email, gender, currentWeight, age, height,registrationDate);
    }
}

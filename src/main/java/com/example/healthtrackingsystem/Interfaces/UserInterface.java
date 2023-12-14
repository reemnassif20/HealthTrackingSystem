package com.example.healthtrackingsystem.Interfaces;


public interface UserInterface {
    List<User> findAll();

    User findById(int userId);

    void save(User user);

    void deleteById(int userId);
}

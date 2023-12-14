package com.example.healthtrackingsystem.Interfaces;
import com.example.healthtrackingsystem.Models.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(int userId);

    void save(User user);

    void deleteById(int userId);
}

package com.example.healthtrackingsystem.Interfaces;
import com.example.healthtrackingsystem.Models.User;
import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(int userId);
    User getUserByEmail(String email);
    void updateUsername(int userId, String newUsername);
    void updatePassword(int userId, String newPassword);

    void save(User user);

    void deleteById(int userId);
}

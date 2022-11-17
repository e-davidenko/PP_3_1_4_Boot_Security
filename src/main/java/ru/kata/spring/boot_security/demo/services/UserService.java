package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 15.11.2022
 * Time: 22:13
 */
public interface UserService {
    List<User> getAllUsers();


    User getUser(int id);

    void updateUser(int id, User user);

    void removeUser(User user);

    void register(User user);

    Optional<User> getCurrentUser();

}


package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO {
    public List<User> getAllUsers();
    public void saveUser(User user);

    User getUser(int id);

    Optional<User> getUser(String username);
    public void updateUser(User user);

    public void removeUser(User user);
}

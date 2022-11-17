package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 15.11.2022
 * Time: 22:39
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override

    @Transactional
    public void updateUser(int id, User user) {
        user.setId(id);
        System.err.println(user.getUsername());
        System.err.println("SERVICE UPDATE USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.err.println(passwordEncoder.encode(user.getPassword()));
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getRole());
        userDAO.saveUser(user);
    }

    @Transactional
    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.err.println("AUTH" + auth.getName());
        return userDAO.getUser(auth.getName());
    }

}

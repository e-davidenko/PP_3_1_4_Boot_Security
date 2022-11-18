package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetails;

import java.util.Optional;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 14.11.2022
 * Time: 21:48
 */
@Service
public class UserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public UserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDAO.getUser(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetails(user.get());
    }




}

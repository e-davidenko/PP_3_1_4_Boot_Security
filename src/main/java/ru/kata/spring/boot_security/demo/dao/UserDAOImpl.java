package ru.kata.spring.boot_security.demo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Created in Intellij
 * User: Davidenko
 * Date: 05.11.2022
 * Time: 20:23
 */

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private RoleRepository roleRepository;
    @Lazy
    public UserDAOImpl(EntityManager entityManager, RoleRepository roleRepository) {
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = entityManager.createQuery("FROM User", User.class).getResultList();
        System.out.println(list.size());
        return list;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> getUser(String username) {
        try {
            System.err.println("ITS HERE");
            Optional<User> user = Optional.ofNullable(entityManager.createQuery("FROM User where username like :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
            if (user.isPresent()) {
                System.out.println("PRESENT USER");
            } else {
                System.err.println("NOT PRESENT");
            }
            return user;
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    @Override
    public void updateUser(User user) {
        System.err.println("TRYING TO MERGE COMMAND");
        entityManager.merge(user);
        System.err.println("MERGE SUCCESS");
    }

    @Override
    public void removeUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}

package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 17.11.2022
 * Time: 15:46
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

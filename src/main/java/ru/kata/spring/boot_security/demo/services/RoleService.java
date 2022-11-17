package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 17.11.2022
 * Time: 15:50
 */

public interface RoleService {

    List<Role> findAllById(List<Integer> ids);
}

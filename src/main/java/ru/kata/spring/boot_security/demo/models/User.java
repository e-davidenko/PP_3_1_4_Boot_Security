package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 14.11.2022
 * Time: 20:55
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    @Min(0)
    @Max(100)
    private int age;


    @NotBlank
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
    }

    public User(String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List <Role> getRole() {
        System.err.println(roles);
        return roles;
    }

    public void setRole(List <Role> roles) {
        this.roles = roles;
    }

    public void addRoleToUser(Role role) {

        if(roles == null) {

            roles = new ArrayList<>();
        }

        roles.add(role);
    }


    public String rolesToString() {

        Role[] roles = getRole().toArray(new Role[getRole().size()]);

        StringBuilder rolesList = new StringBuilder();

        for (int i = 0; i < roles.length; i++) {

            rolesList.append(roles[i].toString() + " ");
        }

        String rolesToReturn = String.valueOf(rolesList).replace("[", "").replace("]","");
        System.err.println(rolesToReturn + " ROLES TO RETURN");
        return rolesToReturn;
    }


}

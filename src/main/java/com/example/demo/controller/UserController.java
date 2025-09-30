package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>(Arrays.asList(
            new User(1L, "Иван", "ivan@example.com"),
            new User(2L, "Мария", "maria@example.com"),
            new User(3L, "Петр", "petr@example.com")
    ));

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                updatedUser.setId(id);
                users.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "User deleted successfully";
    }
}

class User {
    private Long id;
    private String name;
    private String email;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
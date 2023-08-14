package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface AdminService {
    User getUserById(long id);

    User getUserByName(String name);

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(long userId);

    void updateUser(long id, User update);
}

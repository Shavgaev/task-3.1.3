package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
     User getUserById(long id);
     User getUserByName(String name);
     List<User> getUsers();
     void addUser(User user);
     void deleteUser (long userId);
     void updateUser(long id,User update);
}

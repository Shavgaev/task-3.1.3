package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {
    @PersistenceContext
    private EntityManager entityManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Transactional
    public void deleteUser(long userId) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    public void updateUser(long id, User user) {
        User editUser = entityManager.find(User.class, id);
        if (editUser != null) {
            editUser.setUsername(user.getUsername());
            editUser.setEmail(user.getEmail());
            editUser.setRoles(user.getRoles());
            if (!editUser.getPassword().equals(user.getPassword())) {
                editUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
        }
    }
}
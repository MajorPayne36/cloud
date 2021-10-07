package com.example.data.repository;

import com.example.data.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        // language=PostgreSQL
        return entityManager.createQuery("from Users u order by u.id desc", User.class).getResultList();
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}

package com.example.users.repository;

import com.example.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
//@RequiredArgsConstructor
public class UserRepository {
//public interface UserRepository extends JpaRepository<User, String> {
//    @Query("SELECT e FROM users e WHERE e.id = :id")
//    Optional<User> findById(@Param("id") long id);
//
//    @Query("select u from users u order by u.id desc", )
//    List<User> getAll();

//    @Modifying
//    @Transactional
//    @Query("UPDATE UserEntity e SET e.disabled = :disabled WHERE e.id = :id")
//    void disableById(@Param("id") long id, @Param("disabled") boolean disabled);


    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("select u from User u order by u.id desc", User.class).getResultList();
    }

    public User getById(long id) {
        return entityManager.find(User.class, id);
    }
}

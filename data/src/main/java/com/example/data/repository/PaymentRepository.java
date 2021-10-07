package com.example.data.repository;

import com.example.data.data.Payment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PaymentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Payment payment) {
        entityManager.persist(payment);
    }
}

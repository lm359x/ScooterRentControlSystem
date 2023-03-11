package com.example.scooterrentcontrolsystem.dao.impl;

import com.example.scooterrentcontrolsystem.dao.AbstractDao;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.lang.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> daoEntityClass();

    @Override
    public void create(T element) {
        entityManager.persist(element);
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.ofNullable(entityManager.find(daoEntityClass(), id));
    }

    @Override
    public T update(T element) {
        return entityManager.merge(element);
    }

    @Override
    public void delete(T element) {
        entityManager.remove(element);
    }

    @Override
    public List<T> getAll(@NonNull Map<String, Object> mapOfFieldNamesAndValuesToSelectBy,
                          String orderBy,
                          boolean asc,
                          int limit) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(daoEntityClass());
        Root<T> tRoot = cq.from(daoEntityClass());

        Order order = new OrderImpl(tRoot.get(orderBy), asc);
        List<Predicate> predicates = new ArrayList<>();
        mapOfFieldNamesAndValuesToSelectBy.forEach((key, value) -> predicates.add(cb.equal(tRoot.get(key), value)));

        TypedQuery<T> query = entityManager.createQuery(cq
                .select(tRoot)
                .where(predicates.toArray(new Predicate[]{}))
                .orderBy(order)
        );

        return query
                .setMaxResults(limit)
                .getResultList();
    }
}

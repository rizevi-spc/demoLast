package com.example.demo.repository.impl;

import com.example.demo.dto.BookSearchRequest;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.entity.BookStock;
import com.example.demo.repository.DynamicBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DynamicBookRepositoryImpl implements DynamicBookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<BookStock> searchBooks(BookSearchRequest request, PageRequestInfo pageRequestInfo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<BookStock> query = criteriaBuilder.createQuery(BookStock.class);
        final Root<BookStock> from = query.from(BookStock.class);

        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(request.getName())
                .ifPresent(name -> predicates.add(criteriaBuilder.like(from.get("name"), request.getName() + "%")));

        Optional.ofNullable(request.getAuthor())
                .ifPresent(name -> predicates.add(criteriaBuilder.like(from.get("author"), request.getAuthor() + "%")));

        final Predicate allPredicates = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        query.where(allPredicates);
        final List<BookStock> resultList = entityManager.createQuery(query)
                .setFirstResult(pageRequestInfo.getPage())
                .setMaxResults(pageRequestInfo.getSize()).getResultList();
        return new PageImpl<>(resultList, pageRequestInfo.getPageRequest(), getQueryCount(criteriaBuilder, allPredicates));
    }

    private Long getQueryCount(CriteriaBuilder criteriaBuilder, Predicate likeName) {
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(BookStock.class)));
        countQuery.where(likeName);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

package com.example.demo.repository.impl;

import com.example.demo.dto.BookSearchRequest;
import com.example.demo.dto.PageRequestInfo;
import com.example.demo.entity.BookStock;
import com.example.demo.repository.DynamicBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

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
        final Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(from.get("name"),
                        request.getName() + "%"),
                criteriaBuilder.like(from.get("author"), request.getAuthor() + "%"));
        query.where(predicate);
        final List<BookStock> resultList = entityManager.createQuery(query)
                .setFirstResult(pageRequestInfo.getPage())
                .setMaxResults(pageRequestInfo.getSize()).getResultList();
        return new PageImpl<>(resultList, pageRequestInfo.getPageRequest(), getQueryCount(criteriaBuilder, predicate));
    }

    private Long getQueryCount(CriteriaBuilder criteriaBuilder, Predicate likeName) {
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(BookStock.class)));
        countQuery.where(likeName);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

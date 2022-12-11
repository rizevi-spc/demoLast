package com.example.demo.repository;

import com.example.demo.dto.OrderStatistics;
import com.example.demo.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Page<CustomerOrder> findCustomerOrderByCustomer_Id(Long customerId, Pageable pageable);

    Page<CustomerOrder> findCustomerOrderByCreateDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("select new com.example.demo.dto.OrderStatistics(" +
            "month(customerOrder.createDateTime), " +
            "count(customerOrder), " +
            "sum(bookOrder.quantity), " +
            "sum(customerOrder.price)) " +
            "from CustomerOrder customerOrder join customerOrder.bookOrders bookOrder " +
            "group by month(customerOrder.createDateTime)")
    List<OrderStatistics> findOrderStatistics();
}

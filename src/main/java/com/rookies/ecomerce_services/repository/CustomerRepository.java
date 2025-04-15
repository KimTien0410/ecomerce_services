package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUser_UserId(Long userId);

    @Query("SELECT c FROM Customer c JOIN FETCH  c.user")
    Page<Customer> findAllWithUser(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE " +
            "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.user.email) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "c.user.isDeleted = false")
    Page<Customer> searchCustomers(@Param("search") String search, Pageable pageable);
}

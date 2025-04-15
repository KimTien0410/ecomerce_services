package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(@Email String email);


}

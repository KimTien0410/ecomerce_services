package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken,String> {
    boolean existsById(String id);
}

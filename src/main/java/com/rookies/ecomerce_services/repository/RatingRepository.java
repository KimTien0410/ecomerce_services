package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    public List<Rating> findAllByProduct_ProductId(Long productId);
}

package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.FeatureProduct;
import com.rookies.ecomerce_services.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeatureProductRepository extends JpaRepository<FeatureProduct, Long> {
//    @Query("SELECT f FROM FeatureProduct f WHERE Now() BETWEEN f.startDate AND f.endDate")
//    List<FeatureProduct> findActiveFeatureProducts(Pageable pageable);

    @Query("SELECT fp.product FROM FeatureProduct fp WHERE fp.startDate <= CURRENT_TIMESTAMP AND fp.endDate >= CURRENT_TIMESTAMP")
    List<Product> findAllFeatureProducts(Pageable pageable);
}

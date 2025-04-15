package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findByProductIdAndIsDeletedFalse(Long id);
    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findByProductSlugAndIsDeletedFalse(String slug);

    // Tìm tất cả sản phẩm chưa bị xóa

    Page<Product> findAllByIsDeletedFalse(Pageable pageable);

    Page<Product> findAllByProductNameContainingAndIsDeletedFalse(String searchKey, Pageable pageable);
    Page<Product> findAllByCategory_CategoryIdAndIsDeletedFalse(Long categoryId, Pageable pageable);
    Page<Product> findAllByCategory_CategorySlugAndIsDeletedFalse(String categorySlug, Pageable pageable);


    @Query("Select p from Product p Join p.featureProduct fp where p.isDeleted = false and fp.startDate <= current_timestamp and fp.endDate >= current_timestamp")
    Page<Product> findAllByIsDeletedFalseAndIsFeaturedTrue(Pageable pageable);

//    @Query("SELECT p FROM Product p JOIN FeatureProduct f ON p.productId = f.product.productId " +
//            "WHERE p.isDeleted = false and :now BETWEEN f.startDate AND f.endDate")
//    List<Product> getAllFeatureProducts(Pageable pageable);




}

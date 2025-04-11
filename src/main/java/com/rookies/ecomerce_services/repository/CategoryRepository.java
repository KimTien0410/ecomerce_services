package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c WHERE c.categoryId = :id and c.isDeleted = false")
    Optional<Category> findByIdAndIsDeletedFalse(Long id);
    @Query("SELECT c FROM Category c WHERE c.categorySlug = :categorySlug and c.isDeleted = false")
    Optional<Category> findByCategorySlug(@Param("categorySlug") String categorySlug);

    Page<Category> findAllByIsDeletedFalse(Pageable pageable);
}

package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestCategory;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.CategoryResponse;
import com.rookies.ecomerce_services.entity.Admin;
import com.rookies.ecomerce_services.entity.Category;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.CategoryMapper;
import com.rookies.ecomerce_services.repository.CategoryRepository;
import com.rookies.ecomerce_services.service.AdminService;
import com.rookies.ecomerce_services.service.CategoryService;
import com.rookies.ecomerce_services.utils.Slug;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final Slug slug;
    private final AdminService adminService;



    @Transactional
    @Override
    public void addCategory(@RequestBody RequestCategory categoryRequest) {
        Admin admin = adminService.getAuthenticated();
        Category category= categoryMapper.toCategory(categoryRequest);
        category.setCategorySlug(slug.generateSlug(categoryRequest.getCategoryName()));
        category.setCreatedBy(admin);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category= categoryRepository.findByIdAndIsDeletedFalse(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryBySlug(String categorySlug) {
        Category category= categoryRepository.findByCategorySlug(categorySlug)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }
    @Transactional
    @Override
    public void updateCategory(Long categoryId,@RequestBody RequestCategory requestCategory) {
        Admin admin = adminService.getAuthenticated();
        Category category= categoryRepository.findByIdAndIsDeletedFalse(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(category, requestCategory);
        category.setCategorySlug(slug.generateSlug(requestCategory.getCategoryName()));
        category.setLastUpdatedBy(admin);
        categoryRepository.save(category);
    }
    @Transactional
    @Override
    public String deleteCategory(Long categoryId) {
        Admin admin = adminService.getAuthenticated();
        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setDeleted(!category.isDeleted());
        category.setLastUpdatedBy(admin);
        categoryRepository.save(category);
        if(category.isDeleted()){
            return "Xóa danh mục thành công!";
        }
        return "Khôi phục danh mục thành công!";
    }

    @Override
    public Page<CategoryResponse> getAllCategories(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> categories= categoryRepository.findAllByIsDeletedFalse(pageable);

        return categories.map(categoryMapper::toCategoryResponse);
    }

    @Override
    public Category getCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findByIdAndIsDeletedFalse(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}

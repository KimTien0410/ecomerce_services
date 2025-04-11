package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestCategory;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;

public interface CategoryService {
    public void addCategory(RequestCategory categoryRequest);
    public CategoryResponse getCategoryById(Long categoryId);
    public CategoryResponse getCategoryBySlug(String categorySlug);
    public void updateCategory(Long categoryId, RequestCategory requestCategory);
    public String deleteCategory(Long categoryId);
    public Page<CategoryResponse> getAllCategories(int page, int size, String sortBy, String sortDir);

}

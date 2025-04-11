package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestCategory;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.CategoryResponse;
import com.rookies.ecomerce_services.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping()
    public ApiResponse<?> add(@RequestBody @Valid RequestCategory request) {
        categoryService.addCategory(request);
        return ApiResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm mới danh mục thành công!")
                .build();
    }
    @PutMapping("/{categoryId}")
    public ApiResponse<?> update(@PathVariable Long categoryId, @RequestBody @Valid RequestCategory request) {
        categoryService.updateCategory(categoryId, request);
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật danh mục thành công!")
                .build();
    }
    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> getById(@PathVariable Long categoryId) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(categoryId);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh mục thành công!")
                .data(categoryResponse)
                .build();
    }
    @GetMapping("/slug/{categorySlug}")
    public ApiResponse<CategoryResponse> getBySlug(@PathVariable String categorySlug) {
        CategoryResponse categoryResponse = categoryService.getCategoryBySlug(categorySlug);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh mục thành công!")
                .data(categoryResponse)
                .build();
    }
    @PatchMapping("/{categoryId}")
    public ApiResponse<String> delete(@PathVariable Long categoryId) {
        String message = categoryService.deleteCategory(categoryId);
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .build();
    }
    @GetMapping()
    public ApiResponse<Page<CategoryResponse>> getAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "categoryId") String sortBy,
                                                      @RequestParam(defaultValue = "asc") String sortDir) {
        return ApiResponse.<Page<CategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh mục thành công!")
                .data(categoryService.getAllCategories(page, size, sortBy, sortDir))
                .build();
    }

}

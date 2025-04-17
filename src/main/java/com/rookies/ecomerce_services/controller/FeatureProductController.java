package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestFeatureProduct;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.FeatureProductResponse;
import com.rookies.ecomerce_services.service.FeatureProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/feature-products")
@RequiredArgsConstructor
public class FeatureProductController {
    private final FeatureProductService featureProductService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<FeatureProductResponse> addFeatureProduct(@RequestBody RequestFeatureProduct requestFeatureProduct){
        return ApiResponse.<FeatureProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm mới sản phẩm nổi bật thành công")
                .data(featureProductService.addFeatureProduct(requestFeatureProduct))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{featureProductId}")
    public ApiResponse<FeatureProductResponse> updateFeatureProduct(@PathVariable Long featureProductId,
                                                                    @RequestBody RequestFeatureProduct requestFeatureProduct){
        return ApiResponse.<FeatureProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật sản phẩm nổi bật thành công")
                .data(featureProductService.updateFeatureProduct(featureProductId, requestFeatureProduct))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{featureProductId}")
    public ApiResponse<?> deleteFeatureProduct(@PathVariable Long featureProductId){
        featureProductService.deleteFeatureProduct(featureProductId);
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Xóa sản phẩm nổi bật thành công")
                .build();
    }
    @GetMapping("/{featureProductId}")
    public ApiResponse<FeatureProductResponse> getFeatureProductById(@PathVariable Long featureProductId){
        return ApiResponse.<FeatureProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm nổi bật thành công")
                .data(featureProductService.getFeatureProductResponseById(featureProductId))
                .build();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ApiResponse<Page<FeatureProductResponse>> getAllFeatureProduct(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @RequestParam(defaultValue = "priority") String sortBy,
                                                                          @RequestParam(defaultValue = "asc") String sortDir){
        return ApiResponse.<Page<FeatureProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách sản phẩm nổi bật thành công")
                .data(featureProductService.getAllFeature(page, size, sortBy, sortDir))
                .build();
    }
}

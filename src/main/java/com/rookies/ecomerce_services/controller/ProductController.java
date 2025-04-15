package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.ProductItemResponse;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import com.rookies.ecomerce_services.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ApiResponse<ProductResponse> add(@RequestPart @Valid RequestProduct request,
                              @RequestPart(required = false) MultipartFile productImages) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm mới sản phẩm thành công!")
                .data(productService.addProduct(request,productImages))
                .build();
    }
    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> update(@PathVariable Long productId, @RequestPart @Valid RequestProduct request,
                                 @RequestPart(required = false) MultipartFile productImages) {

        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật sản phẩm thành công!")
                .data(productService.updateProduct(productId, request,productImages))
                .build();
    }
    @GetMapping()
    public ApiResponse<Page<ProductItemResponse>> getAllProduct(@RequestParam(required = false) String search,
                                                                @RequestParam(required = false,defaultValue ="desc" ) String sort,
                                                                @RequestParam(defaultValue = "productId") String sortBy,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductItemResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm theo danh mục thành công!")
                .data(productService.getAllProducts(page, size, sortBy, sort, search))
                .build();
    }
    @GetMapping("/features")
    public ApiResponse<List<ProductItemResponse>> getAllProduct(
                                                                @RequestParam(required = false,defaultValue ="desc" ) String sort,
                                                                @RequestParam(defaultValue = "priority") String sortBy,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<List<ProductItemResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm theo danh mục thành công!")
                .data(productService.getAllProductFeature(page, size, sortBy, sort))
                .build();
    }
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long productId) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm thành công!")
                .data(productService.getProductById(productId))
                .build();
    }
    @GetMapping("/slug/{productSlug}")
    public ApiResponse<ProductResponse> getBySlug(@PathVariable String productSlug) {
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm thành công!")
                .data(productService.getProductBySlug(productSlug))
                .build();
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<Page<ProductItemResponse>> getAllProductByCategoryId(@PathVariable Long categoryId,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @RequestParam(defaultValue = "productId") String sortBy,
                                                                          @RequestParam(defaultValue = "desc") String sort) {
        return ApiResponse.<Page<ProductItemResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm theo danh mục thành công!")
                .data(productService.getAllProductsByCategoryId(categoryId, page, size, sortBy, sort))
                .build();
    }
    @GetMapping("category-slug/{categorySlug}")
    public ApiResponse<Page<ProductItemResponse>> getAllProductByCategorySlug(@PathVariable String categorySlug,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size,
                                                                           @RequestParam(defaultValue = "productId") String sortBy,
                                                                           @RequestParam(defaultValue = "desc") String sort) {
        return ApiResponse.<Page<ProductItemResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy sản phẩm theo danh mục thành công!")
                .data(productService.getAllProductsByCategorySlug(categorySlug, page, size, sortBy, sort))
                .build();
    }
    @PatchMapping("/{productId}")
    public ApiResponse<String> delete(@PathVariable Long productId) {
        String message = productService.deleteAndRestore(productId);
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .build();
    }
}

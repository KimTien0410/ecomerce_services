package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    public ProductResponse addProduct(RequestProduct requestProduct, MultipartFile productImages);
    public ProductResponse updateProduct(Long id, RequestProduct requestProduct,MultipartFile productImages);
    public String deleteAndRestore(Long id);
    public ProductResponse getProductById(Long id);
    public ProductResponse getProductBySlug(String slug);
    public Page<ProductResponse> getAllProducts(int page, int size, String sortBy, String sortDir, String search, boolean isFeatured);
    public Page<ProductResponse> getAllProductsByCategoryId(Long categoryId, int page, int size, String sortBy, String sortDir);
    public Page<ProductResponse> getAllProductsByCategorySlug(String categorySlug, int page, int size, String sortBy, String sortDir);



}

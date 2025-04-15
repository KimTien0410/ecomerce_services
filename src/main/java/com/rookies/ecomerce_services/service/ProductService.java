package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ProductItemResponse;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import com.rookies.ecomerce_services.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public ProductResponse addProduct(RequestProduct requestProduct, MultipartFile productImages);
    public ProductResponse updateProduct(Long id, RequestProduct requestProduct,MultipartFile productImages);
    public String deleteAndRestore(Long id);
    public ProductResponse getProductById(Long id);
    public ProductResponse getProductBySlug(String slug);
    public Page<ProductItemResponse> getAllProducts(int page, int size, String sortBy, String sortDir, String search);
    public Page<ProductItemResponse> getAllProductsByCategoryId(Long categoryId, int page, int size, String sortBy, String sortDir);
    public Page<ProductItemResponse> getAllProductsByCategorySlug(String categorySlug, int page, int size, String sortBy, String sortDir);
    public List<ProductItemResponse> getAllProductFeature(int page, int size, String sortBy, String sortDir);
    public Product getByProductId(Long id);

}

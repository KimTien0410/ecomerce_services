package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import com.rookies.ecomerce_services.entity.Category;
import com.rookies.ecomerce_services.entity.Product;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.ProductMapper;
import com.rookies.ecomerce_services.repository.CategoryRepository;
import com.rookies.ecomerce_services.repository.ProductRepository;
import com.rookies.ecomerce_services.service.ProductService;
import com.rookies.ecomerce_services.utils.CloudinaryService;
import com.rookies.ecomerce_services.utils.Slug;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final Slug slug;
    private final CloudinaryService cloudinaryService;


    @Transactional
    @Override
    public ProductResponse addProduct(RequestProduct requestProduct,MultipartFile file) {
        Product product = productMapper.toProduct(requestProduct);
        Category category = categoryRepository.findByIdAndIsDeletedFalse(requestProduct.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);

        product.setProductSlug(slug.generateSlug(requestProduct.getProductName()));

        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(file);
            product.setProductImages(imageUrl);
        }

        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }
    @Transactional
    @Override
    public ProductResponse updateProduct(Long id, RequestProduct requestProduct,MultipartFile file) {
        Product product = productRepository.findByProductIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProduct(product, requestProduct);
        Category category = categoryRepository.findByIdAndIsDeletedFalse(requestProduct.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        product.setCategory(category);
        product.setProductSlug(slug.generateSlug(requestProduct.getProductName()));

        if (file != null && !file.isEmpty()) {
            // Xoá ảnh cũ trước khi upload ảnh mới
            if(product.getProductImages() != null) {
                cloudinaryService.deleteOldFile(product.getProductImages());
            }
            String imageUrl = cloudinaryService.uploadFile(file);
            product.setProductImages(imageUrl);
        }
        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    @Transactional
    @Override
    public String deleteAndRestore(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
//        product.setDeleted(!product.isDeleted());
//        productRepository.save(product);
//        if (product.isDeleted()) {
//            return "Xoá sản phẩm thành công!";
//        }
        return "Khôi phục sản phẩm thành công!";
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findByProductIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        ProductResponse productResponse = productMapper.toProductResponse(product);
        Category category = product.getCategory();
        if (category != null) {
            productResponse.setCategoryId(category.getCategoryId());
            productResponse.setCategoryName(category.getCategoryName());
        }
        return productResponse;
    }

    @Override
    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findByProductSlugAndIsDeletedFalse(slug)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        ProductResponse productResponse = productMapper.toProductResponse(product);
        Category category = product.getCategory();
        if (category != null) {
            productResponse.setCategoryId(category.getCategoryId());
            productResponse.setCategoryName(category.getCategoryName());
        }
        return  productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(int page, int size, String sortBy, String sortDirection, String search,boolean isFeatured) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if(search != null && !search.isEmpty()) {
            return productRepository.findAllByProductNameContainingAndIsDeletedFalse(search, pageable)
                    .map(productMapper::toProductResponse);
        }
        if(isFeatured){
//            Page<Product> products = productRepository.findAllByIsDeletedFalseAndIsFeaturedTrue(pageable);
//            return products.map(productMapper::toProductResponse);
        }
        Page<Product> products = productRepository.findAllByIsDeletedFalse(pageable);
        return products.map(productMapper::toProductResponse);
    }

    @Override
    public Page<ProductResponse> getAllProductsByCategoryId(Long categoryId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAllByCategory_CategoryIdAndIsDeletedFalse(categoryId,pageable);
        return products.map(productMapper::toProductResponse);
    }

    @Override
    public Page<ProductResponse> getAllProductsByCategorySlug(String categorySlug, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAllByCategory_CategorySlugAndIsDeletedFalse(categorySlug,pageable);

        return products.map(productMapper::toProductResponse);
    }
}

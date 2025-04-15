package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ProductItemResponse;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import com.rookies.ecomerce_services.entity.Category;
import com.rookies.ecomerce_services.entity.Product;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.ProductMapper;
import com.rookies.ecomerce_services.repository.CategoryRepository;
import com.rookies.ecomerce_services.repository.FeatureProductRepository;
import com.rookies.ecomerce_services.repository.ProductRepository;
import com.rookies.ecomerce_services.service.CategoryService;
import com.rookies.ecomerce_services.service.FeatureProductService;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final Slug slug;
    private final CloudinaryService cloudinaryService;
    private final FeatureProductRepository featureProductRepository;


    @Transactional
    @Override
    public ProductResponse addProduct(RequestProduct requestProduct,MultipartFile file) {
        Product product = productMapper.toProduct(requestProduct);
        Category category = categoryService.getCategoryByCategoryId(requestProduct.getCategoryId());
        product.setCategory(category);
        product.setProductSlug(slug.generateSlug(requestProduct.getProductName()));
        System.out.println(slug.generateSlug(requestProduct.getProductName()));
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
        Product product = getByProductId(id);
        productMapper.updateProduct(product, requestProduct);
        Category category = categoryService.getCategoryByCategoryId(requestProduct.getCategoryId());
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
        Product product = getByProductId(id);
        product.setIsDeleted(!product.getIsDeleted());
        productRepository.save(product);
        if (product.getIsDeleted()) {
            return "Xoá sản phẩm thành công!";
        }
        return "Khôi phục sản phẩm thành công!";
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findByProductIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductResponse(product);
//        Category category = product.getCategory();
//        if (category != null) {
//            productResponse.setCategoryId(category.getCategoryId());
//            productResponse.setCategoryName(category.getCategoryName());
//        }
//        return productResponse;
    }

    @Override
    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findByProductSlugAndIsDeletedFalse(slug)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return  productMapper.toProductResponse(product);
    }

    @Override
    public Page<ProductItemResponse> getAllProducts(int page, int size, String sortBy, String sortDirection, String search) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if(search != null && !search.isEmpty()) {
            return productRepository.findAllByProductNameContainingAndIsDeletedFalse(search, pageable)
                    .map(productMapper::toProductItemResponse);
        }
        Page<Product> products = productRepository.findAllByIsDeletedFalse(pageable);
        return products.map(productMapper::toProductItemResponse);
    }

    @Override
    public Page<ProductItemResponse> getAllProductsByCategoryId(Long categoryId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAllByCategory_CategoryIdAndIsDeletedFalse(categoryId,pageable);
        return products.map(productMapper::toProductItemResponse);
    }

    @Override
    public Page<ProductItemResponse> getAllProductsByCategorySlug(String categorySlug, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = productRepository.findAllByCategory_CategorySlugAndIsDeletedFalse(categorySlug,pageable);

        return products.map(productMapper::toProductItemResponse);
    }

    @Override
    public List<ProductItemResponse> getAllProductFeature(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        if (sortDir.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Product> products =featureProductRepository.findAllFeatureProducts(pageable);
        return productMapper.toProductItemResponseList(products);
    }

    @Override
    public Product getByProductId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Transactional
    @Override
    public void updateProductRating(Long id, double rating) {
        Product product = getByProductId(id);
        if(product.getAverageRating()==0.0){
            product.setAverageRating(rating);
        }
        else{
            product.setAverageRating((product.getAverageRating() + rating) / 2);
        }
        productRepository.save(product);
    }
}

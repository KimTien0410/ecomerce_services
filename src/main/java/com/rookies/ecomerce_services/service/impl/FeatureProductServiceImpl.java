package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestFeatureProduct;
import com.rookies.ecomerce_services.dto.response.FeatureProductResponse;
import com.rookies.ecomerce_services.entity.Admin;
import com.rookies.ecomerce_services.entity.FeatureProduct;
import com.rookies.ecomerce_services.entity.Product;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.FeatureProductMapper;
import com.rookies.ecomerce_services.repository.FeatureProductRepository;
import com.rookies.ecomerce_services.service.AdminService;
import com.rookies.ecomerce_services.service.FeatureProductService;
import com.rookies.ecomerce_services.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureProductServiceImpl implements FeatureProductService {
    private final FeatureProductRepository featureProductRepository;
    private final ProductService productService;
    private final FeatureProductMapper featureProductMapper;
    private final AdminService adminService;

    @Override
    @Transactional
    public FeatureProductResponse addFeatureProduct(RequestFeatureProduct requestFeatureProduct) {
        FeatureProduct featureProduct = featureProductMapper.requestToFeatureProduct(requestFeatureProduct);
        Product product = productService.getByProductId(requestFeatureProduct.getProductId());
        featureProduct.setProduct(product);
        Admin admin= adminService.getAuthenticated();
        product.setCreatedBy(admin);
        featureProductRepository.save(featureProduct);
        return featureProductMapper.featureProductToResponse(featureProduct);
    }

    @Override
    @Transactional
    public FeatureProductResponse updateFeatureProduct(Long featureProductId, RequestFeatureProduct requestFeatureProduct) {
       FeatureProduct featureProduct=getFeatureProductById(featureProductId);
        featureProductMapper.updateFeatureProduct(featureProduct, requestFeatureProduct);
//        Product product = productService.getByProductId(requestFeatureProduct.getProductId());
//        featureProduct.setProduct(product);
        Admin admin= adminService.getAuthenticated();
        featureProduct.setLastUpdatedBy(admin);
        featureProductRepository.save(featureProduct);
        return featureProductMapper.featureProductToResponse(featureProduct);
    }

    @Override
    @Transactional
    public void deleteFeatureProduct(Long featureProductId) {
        FeatureProduct featureProduct=getFeatureProductById(featureProductId);
        Admin admin= adminService.getAuthenticated();
        featureProduct.setLastUpdatedBy(admin);
        featureProductRepository.delete(featureProduct);
    }

    @Override
    public Page<FeatureProductResponse> getAllFeature(int page, int size, String sortBy, String sortDir) {
        Sort sort = Sort.by(sortBy);
        if (sortDir.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<FeatureProduct> featureProducts = featureProductRepository.findAll(pageable);
        return featureProducts.map(featureProductMapper::featureProductToResponse);
    }

    @Override
    public FeatureProduct getFeatureProductById(Long featureProductId) {
        return featureProductRepository.findById(featureProductId)
                .orElseThrow(() -> new AppException(ErrorCode.FEATURE_PRODUCT_NOT_FOUND));
    }

    @Override
    public FeatureProductResponse getFeatureProductResponseById(Long featureProductId) {
        FeatureProduct featureProduct = getFeatureProductById(featureProductId);
        return featureProductMapper.featureProductToResponse(featureProduct);
    }
}

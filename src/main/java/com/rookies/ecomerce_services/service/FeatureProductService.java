package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestFeatureProduct;
import com.rookies.ecomerce_services.dto.response.FeatureProductResponse;
import com.rookies.ecomerce_services.entity.FeatureProduct;
import com.rookies.ecomerce_services.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeatureProductService {
    public FeatureProductResponse addFeatureProduct(RequestFeatureProduct requestFeatureProduct);
    public FeatureProductResponse updateFeatureProduct(Long featureProductId, RequestFeatureProduct requestFeatureProduct);
    public void deleteFeatureProduct(Long featureProductId);
    public Page<FeatureProductResponse> getAllFeature(int page, int size, String sortBy,String sortDir);

    public FeatureProduct getFeatureProductById(Long featureProductId);
    public FeatureProductResponse getFeatureProductResponseById(Long featureProductId);

}

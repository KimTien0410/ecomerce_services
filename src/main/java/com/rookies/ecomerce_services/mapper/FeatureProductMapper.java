package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestFeatureProduct;
import com.rookies.ecomerce_services.dto.response.FeatureProductResponse;
import com.rookies.ecomerce_services.entity.FeatureProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FeatureProductMapper {
    FeatureProduct requestToFeatureProduct(RequestFeatureProduct requestFeatureProduct);

    void updateFeatureProduct(@MappingTarget FeatureProduct featureProduct, RequestFeatureProduct requestFeatureProduct);

    FeatureProductResponse featureProductToResponse(FeatureProduct featureProduct);
}

package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ProductItemResponse;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import com.rookies.ecomerce_services.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(RequestProduct requestProduct);


    void updateProduct(@MappingTarget Product product, RequestProduct requestProduct);

    ProductResponse toProductResponse(Product product);

    ProductItemResponse toProductItemResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);

    List<ProductItemResponse> toProductItemResponseList(List<Product> products);


}

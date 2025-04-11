package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestProduct;
import com.rookies.ecomerce_services.dto.response.ProductResponse;
import com.rookies.ecomerce_services.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(target ="productImages",ignore = true)
    Product toProduct(RequestProduct requestProduct);


//    @Mapping(target = "productImages", ignore = true)
    void updateProduct(@MappingTarget Product product, RequestProduct requestProduct);


    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);

}

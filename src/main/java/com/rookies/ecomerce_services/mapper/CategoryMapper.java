package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestCategory;
import com.rookies.ecomerce_services.dto.response.CategoryResponse;
import com.rookies.ecomerce_services.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(RequestCategory requestCategory);

    CategoryResponse toCategoryResponse(Category category);

    void updateCategory(@MappingTarget Category category, RequestCategory requestCategory);


}

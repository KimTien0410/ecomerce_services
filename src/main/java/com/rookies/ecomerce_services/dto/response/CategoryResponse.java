package com.rookies.ecomerce_services.dto.response;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    private String categorySlug;
    private String categoryDescription;
    private Date createdOn;
    private Date lastUpdatedOn;
}

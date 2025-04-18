package com.rookies.ecomerce_services.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private String productSlug;
    private double productPrice;
    private int stockQuantity;
    private String productImages;
    private String productDescription;
    private Double averageRating;
    private CategoryResponse category;
    private Date createdOn;
    private Date lastUpdatedOn;

}

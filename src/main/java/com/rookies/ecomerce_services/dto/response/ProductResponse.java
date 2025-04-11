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
    private double productPrice;
    private int stockQuantity;
    private String productImages;
    private String productDescription;
    private Boolean isFeatured;
    private Double averageRating;
    private Long categoryId;
    private String categoryName;
    private Date createdOn;
    private Date lastUpdatedOn;

}

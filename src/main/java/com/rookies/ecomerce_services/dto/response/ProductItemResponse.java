package com.rookies.ecomerce_services.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemResponse {
    private Long productId;
    private String productName;
    private String productSlug;
    private double productPrice;
    private int stockQuantity;
    private String productImages;
    private double averageRating;
}

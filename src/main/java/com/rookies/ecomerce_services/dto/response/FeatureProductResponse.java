package com.rookies.ecomerce_services.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureProductResponse {
    private Long featureProductId;
//    private ProductItemResponse product;
    private String featureProductDescription;
    private Date startDate;
    private Date endDate;
    private int priority;


}

package com.rookies.ecomerce_services.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestFeatureProduct {
    private Long productId;
    private String featureProductDescription;
    private Date startDate;
    private Date endDate;
    private int priority;
}

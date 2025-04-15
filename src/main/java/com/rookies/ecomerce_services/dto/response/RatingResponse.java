package com.rookies.ecomerce_services.dto.response;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private Long ratingId;
    private Long productId;
    private Long customerId;
    private String customerName;
    private int ratingStar;
    private String comment;
    private Date createdOn;
}

package com.rookies.ecomerce_services.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRating {
    private Long userId;
    private Long productId;
    private int ratingStar;
    private String comment;
}

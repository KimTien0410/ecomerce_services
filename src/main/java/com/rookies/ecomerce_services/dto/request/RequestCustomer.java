package com.rookies.ecomerce_services.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCustomer {
    private String firstName;
    private String lastName;
}

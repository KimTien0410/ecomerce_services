package com.rookies.ecomerce_services.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestAddAdmin {
    private String email;
    private String password;

}

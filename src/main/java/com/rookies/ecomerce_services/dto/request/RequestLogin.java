package com.rookies.ecomerce_services.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLogin {
    private String email;
    private String password;
}

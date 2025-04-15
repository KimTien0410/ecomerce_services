package com.rookies.ecomerce_services.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String email;
    private String password;
}

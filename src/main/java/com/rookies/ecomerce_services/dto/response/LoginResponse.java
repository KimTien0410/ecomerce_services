package com.rookies.ecomerce_services.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String email;
    private RoleResponse role;
}

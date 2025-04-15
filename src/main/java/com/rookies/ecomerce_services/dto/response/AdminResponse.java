package com.rookies.ecomerce_services.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private Long adminId;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String avatar;
}

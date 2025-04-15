package com.rookies.ecomerce_services.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Long roleId;
    private String roleName;
}

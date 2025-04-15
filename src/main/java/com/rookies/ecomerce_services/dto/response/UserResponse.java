package com.rookies.ecomerce_services.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String email;
    private RoleResponse role;
    private Date createdOn;
    private Date lastUpdatedOn;
}

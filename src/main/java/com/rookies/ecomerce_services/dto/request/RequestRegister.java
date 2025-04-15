package com.rookies.ecomerce_services.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestRegister {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}

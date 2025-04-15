package com.rookies.ecomerce_services.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUser {
    @NotBlank(message="EMAIL_NOT_BLANK")
    @Email
    private String email;
    @Min(value = 6, message = "PASSWORD_MIN")
    private String password;
    private String firstName;
    private String lastName;

}

package com.rookies.ecomerce_services.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUpdateAdmin {
    private String firstName;
    private String lastName;
}

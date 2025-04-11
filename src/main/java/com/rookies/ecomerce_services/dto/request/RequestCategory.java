package com.rookies.ecomerce_services.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCategory {
    @NotBlank(message = "CATEGORY_NAME_NOT_BLANK")
    private String categoryName;
    private String categoryDescription;
}

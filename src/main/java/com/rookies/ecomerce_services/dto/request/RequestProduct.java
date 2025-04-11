package com.rookies.ecomerce_services.dto.request;

import com.rookies.ecomerce_services.validation.ValidFile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {
    @NotBlank(message="PRODUCT_NAME_NOT_BLANK")
    private String productName;
    @NotNull(message="CATEGORY_ID_NOT_NULL")
    private Long categoryId;
    @NotNull(message="PRODUCT_PRICE_NOT_NULL")
    @Min(value = 1, message = "PRODUCT_PRICE_MIN")
    private double productPrice;
    @Min(value = 1, message = "STOCK_QUANTITY_MIN")
    @NotNull(message="STOCK_QUANTITY_NOT_NULL")
    private int stockQuantity;
//    @ValidFile(message = "PRODUCT_IMAGES_NOT_VALID")
//    private MultipartFile productImages;
    private String productDescription;
    private Boolean isFeatured;
}

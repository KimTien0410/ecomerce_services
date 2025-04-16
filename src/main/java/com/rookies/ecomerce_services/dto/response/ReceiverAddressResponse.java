package com.rookies.ecomerce_services.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverAddressResponse {
    private Long receiverAddressId;
    private String receiverName;
    private String receiverPhone;
    private String receiverStreet;
    private String receiverWard;
    private String receiverDistrict;
    private String receiverCity;
    private String receiverAddress;
    private Boolean isDefault;
}

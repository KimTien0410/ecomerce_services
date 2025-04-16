package com.rookies.ecomerce_services.dto.request;

import com.rookies.ecomerce_services.entity.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestReceiverAddress {
    @NotNull(message="CUSTOMER_ID_NOT_NULL")
    private Long customerId;
    @NotBlank(message="RECEIVER_NAME_NOT_BLANK")
    private String receiverName;
    @NotBlank(message="RECEIVER_PHONE_NOT_BLANK")
    private String receiverPhone;
    @NotBlank(message="RECEIVER_STREET_NOT_BLANK")
    private String receiverStreet;
    @NotBlank(message="RECEIVER_WARD_NOT_BLANK")
    private String receiverWard;
    @NotBlank(message="RECEIVER_DISTRICT_NOT_BLANK")
    private String receiverDistrict;
    @NotBlank(message="RECEIVER_CITY_NOT_BLANK")
    private String receiverCity;
}

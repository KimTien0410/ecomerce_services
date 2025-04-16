package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestReceiverAddress;
import com.rookies.ecomerce_services.dto.response.ReceiverAddressResponse;
import com.rookies.ecomerce_services.entity.ReceiverAddress;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiverAddressMapper {
    ReceiverAddress toReceiverAddress(RequestReceiverAddress requestAddReceiverAddress);

    void updateReceiverAddress(@MappingTarget ReceiverAddress receiverAddress, RequestReceiverAddress requestUpdateReceiverAddress);

    ReceiverAddressResponse toReceiverAddressResponse(ReceiverAddress receiverAddress);

    List<ReceiverAddressResponse> toReceiverAddressResponseList(List<ReceiverAddress> receiverAddresses);
}

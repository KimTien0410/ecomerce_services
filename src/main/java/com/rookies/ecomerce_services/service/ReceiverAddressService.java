package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestReceiverAddress;
import com.rookies.ecomerce_services.dto.response.ReceiverAddressResponse;
import com.rookies.ecomerce_services.entity.ReceiverAddress;

import java.util.List;
import java.util.Optional;

public interface ReceiverAddressService {
    public ReceiverAddressResponse addReceiverAddress(RequestReceiverAddress request);
    public ReceiverAddressResponse updateReceiverAddress(Long addressId, RequestReceiverAddress request);
    public ReceiverAddressResponse getReceiverAddressById(Long addressId);
    public List<ReceiverAddressResponse> getReceiverAddressByCustomer();
    public void deleteReceiverAddress(Long addressId);
    public void setDefaultReceiverAddress(Long addressId);
    public ReceiverAddressResponse getDefaultReceiverAddressResponse();
    public ReceiverAddress getDefaultReceiverAddress();
    public ReceiverAddress findById(Long addressId);

}

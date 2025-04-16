package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestReceiverAddress;
import com.rookies.ecomerce_services.dto.response.ReceiverAddressResponse;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.ReceiverAddress;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.ReceiverAddressMapper;
import com.rookies.ecomerce_services.repository.ReceiverAddressRepository;
import com.rookies.ecomerce_services.service.CustomerService;
import com.rookies.ecomerce_services.service.ReceiverAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiverAddressServiceImpl implements ReceiverAddressService {
    private final ReceiverAddressRepository receiverAddressRepository;
    private final CustomerService customerService;
    private final ReceiverAddressMapper receiverAddressMapper;
    @Override
    public ReceiverAddressResponse addReceiverAddress( RequestReceiverAddress request) {
        Customer customer = customerService.findByCustomerId(request.getCustomerId());
        ReceiverAddress receiverAddress= receiverAddressMapper.toReceiverAddress(request);
        receiverAddress.setCustomer(customer);
        receiverAddress.setIsDefault(false);
        String receiverAddressFull =receiverAddress.getReceiverStreet()+", "+receiverAddress.getReceiverWard()
                +", "+receiverAddress.getReceiverDistrict()+", "+receiverAddress.getReceiverCity();
        receiverAddress.setReceiverAddress(receiverAddressFull);
        receiverAddressRepository.save(receiverAddress);
        return receiverAddressMapper.toReceiverAddressResponse(receiverAddress);
    }

    @Override
    public ReceiverAddressResponse updateReceiverAddress(Long addressId, RequestReceiverAddress request) {
        ReceiverAddress receiverAddress = receiverAddressRepository.findById(addressId)
                .orElseThrow(()->new AppException(ErrorCode.RECEIVER_ADDRESS_NOT_FOUND));
        receiverAddressMapper.updateReceiverAddress(receiverAddress, request);
        String receiverAddressFull =receiverAddress.getReceiverStreet()+", "+receiverAddress.getReceiverWard()
                +", "+receiverAddress.getReceiverDistrict()+", "+receiverAddress.getReceiverCity();
        receiverAddress.setReceiverAddress(receiverAddressFull);
        receiverAddressRepository.save(receiverAddress);
        return receiverAddressMapper.toReceiverAddressResponse(receiverAddress);
    }

    @Override
    public List<ReceiverAddressResponse> getReceiverAddressByCustomer(Long customerId) {
        Customer customer = customerService.findByCustomerId(customerId);
        List<ReceiverAddress> receiverAddresses = receiverAddressRepository.findAllByCustomer(customer);
        return receiverAddressMapper.toReceiverAddressResponseList(receiverAddresses);
    }

    @Override
    public void deleteReceiverAddress(Long addressId) {
        ReceiverAddress receiverAddress = findById(addressId);
        receiverAddressRepository.delete(receiverAddress);
    }

    @Override
    public void setDefaultReceiverAddress(Long addressId) {
        ReceiverAddress receiverAddress = findById(addressId);
        Customer customer = receiverAddress.getCustomer();
        ReceiverAddress defaultAddress=receiverAddressRepository.findByCustomerAndIsDefaultTrue(customer).orElse(null);
        if (defaultAddress != null) {
            defaultAddress.setIsDefault(false);
            receiverAddressRepository.save(defaultAddress);
        }
        receiverAddress.setIsDefault(true);
        receiverAddressRepository.save(receiverAddress);
    }

    @Override
    public ReceiverAddressResponse getDefaultReceiverAddressResponse(Long userId) {
        return receiverAddressMapper.toReceiverAddressResponse(getDefaultReceiverAddress(userId));
    }

    @Override
    public ReceiverAddress findById(Long addressId) {
        return receiverAddressRepository.findById(addressId).orElseThrow(()->
                new AppException(ErrorCode.RECEIVER_ADDRESS_NOT_FOUND));
    }

    @Override
    public ReceiverAddressResponse getReceiverAddressById(Long addressId) {
        ReceiverAddress receiverAddress = receiverAddressRepository.findById(addressId).orElseThrow(()->
                new AppException(ErrorCode.RECEIVER_ADDRESS_NOT_FOUND));
        return receiverAddressMapper.toReceiverAddressResponse(receiverAddress);
    }

    @Override
    public ReceiverAddress getDefaultReceiverAddress(Long userId) {
        ReceiverAddress receiverAddress= receiverAddressRepository.findByCustomerAndIsDefaultTrue(customerService.findByUserId(userId))
                .orElseThrow(() -> new AppException(ErrorCode.RECEIVER_ADDRESS_NOT_FOUND));
        return receiverAddress;
    }
}

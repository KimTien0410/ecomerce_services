package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestReceiverAddress;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.ReceiverAddressResponse;
import com.rookies.ecomerce_services.service.ReceiverAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/receiver-addresses")
@RequiredArgsConstructor
public class ReceiverAddressController {
    private final ReceiverAddressService receiverAddressService;
    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ApiResponse<ReceiverAddressResponse> addReceiverAddress(@RequestBody RequestReceiverAddress receiverAddress) {
        return ApiResponse.<ReceiverAddressResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm địa chỉ nhận hàng thành công!")
                .data(receiverAddressService.addReceiverAddress(receiverAddress))
                .build();
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ApiResponse<List<ReceiverAddressResponse>> getReceiverAddressByUserId() {
        return ApiResponse.<List<ReceiverAddressResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin địa chỉ nhận hàng thành công!")
                .data(receiverAddressService.getReceiverAddressByCustomer())
                .build();
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ApiResponse<ReceiverAddressResponse> updateReceiverAddress(@PathVariable Long id, @RequestBody RequestReceiverAddress receiverAddress) {
        return ApiResponse.<ReceiverAddressResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật địa chỉ nhận hàng thành công!")
                .data(receiverAddressService.updateReceiverAddress(id, receiverAddress))
                .build();
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/default")
    public ApiResponse<ReceiverAddressResponse> getDefaultReceiverAddress() {
        return ApiResponse.<ReceiverAddressResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy địa chỉ nhận hàng mặc định thành công!")
                .data(receiverAddressService.getDefaultReceiverAddressResponse())
                .build();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteReceiverAddress(@PathVariable Long id) {
        receiverAddressService.deleteReceiverAddress(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa địa chỉ nhận hàng thành công!")
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/default/{id}")
    public ApiResponse<ReceiverAddressResponse> setDefaultReceiverAddress(@PathVariable Long id) {
        receiverAddressService.setDefaultReceiverAddress(id);
        return ApiResponse.<ReceiverAddressResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật địa chỉ nhận hàng mặc định thành công!")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ReceiverAddressResponse> getReceiverAddressById(@PathVariable Long id) {
        return ApiResponse.<ReceiverAddressResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin địa chỉ nhận hàng thành công!")
                .data(receiverAddressService.getReceiverAddressById(id))
                .build();
    }



}

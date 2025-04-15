package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestCustomer;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.CustomerResponse;
import com.rookies.ecomerce_services.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/profile")
    public ApiResponse<CustomerResponse> getProfile(@RequestParam Long userId) {
        return ApiResponse.<CustomerResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin tài khoản thành công!")
                .data(customerService.getProfile(userId))
                .build();
    }

    @PutMapping("/profile")
    public ApiResponse<CustomerResponse> updateProfile(@RequestParam Long userId , @RequestPart(name="customer") RequestCustomer request,
                                                       @RequestPart(name = "avatar", required = false) MultipartFile file) {
        customerService.updateProfile(userId, request, file);
        return ApiResponse.<CustomerResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật thông tin tài khoản thành công!")
                .build();
    }

    @GetMapping()
    public ApiResponse<?> getAllCustomers(@RequestParam(required = false) String search,
                                        @RequestParam(required = false,defaultValue = "0")int page,
                                        @RequestParam(required = false,defaultValue = "5") int size,
                                        @RequestParam(required = false,defaultValue ="customerId" )  String sortBy,
                                        @RequestParam(required = false,defaultValue = "ASC")  String sortDir) {
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách tài khoản thành công!")
                .data(customerService.getAllCustomers(page, size, sortBy, sortDir,search))
                .build();
    }
    @PatchMapping("/{id}")
    public ApiResponse<?> deleteRestoreCustomer(@PathVariable Long id) {
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message(customerService.deleteAndRestore(id))
                .build();
    }
}

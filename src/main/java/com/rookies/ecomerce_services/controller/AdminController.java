package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestAddAdmin;
import com.rookies.ecomerce_services.dto.request.RequestUpdateAdmin;
import com.rookies.ecomerce_services.dto.response.AdminResponse;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ApiResponse<AdminResponse> addAdmin(@RequestBody RequestAddAdmin request) {
        return ApiResponse.<AdminResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm mới admin thành công!")
                .data(adminService.addAdmin(request))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<AdminResponse> getAdminById(@PathVariable Long id) {
        return ApiResponse.<AdminResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin admin thành công!")
                .data(adminService.getAdminById(id))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<AdminResponse> updateAdmin(@PathVariable Long id, @RequestPart RequestUpdateAdmin request,
                                                  @RequestPart(required = false) MultipartFile file) {
        return ApiResponse.<AdminResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật thông tin admin thành công!")
                .data(adminService.updateAdmin(id, request, file))
                .build();
    }


}

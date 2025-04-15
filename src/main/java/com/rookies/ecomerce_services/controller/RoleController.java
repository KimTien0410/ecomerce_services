package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestRole;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.RoleResponse;
import com.rookies.ecomerce_services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping()
    public ApiResponse<RoleResponse> addRole(@RequestBody RequestRole request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Thêm mới quyền thành công!")
                .data(roleService.addRole(request))
                .build();
    }
    @PutMapping("/{roleId}")
    public ApiResponse<RoleResponse> updateRole(@PathVariable Long roleId, @RequestBody RequestRole request) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Cập nhật quyền thành công!")
                .data(roleService.updateRole(roleId, request))
                .build();
    }
    @GetMapping("/{roleId}")
    public ApiResponse<RoleResponse> getRoleById(@PathVariable Long roleId) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Lấy quyền thành công!")
                .data(roleService.getRoleById(roleId))
                .build();
    }
    @GetMapping("/name/{roleName}")
    public ApiResponse<RoleResponse> getRoleByName(@PathVariable String roleName) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("Lấy quyền thành công!")
                .data(roleService.getRoleByName(roleName))
                .build();
    }
    @GetMapping()
    public ApiResponse<?> getAllRoles() {
        return ApiResponse.builder()
                .code(200)
                .message("Lấy tất cả quyền thành công!")
                .data(roleService.getAllRoles())
                .build();
    }
}

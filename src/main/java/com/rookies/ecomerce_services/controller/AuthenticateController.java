package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestLogin;
import com.rookies.ecomerce_services.dto.request.RequestRegister;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.LoginResponse;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;
import com.rookies.ecomerce_services.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticateController {
    private final AuthenticateService authenticateService;
    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@RequestBody RequestRegister request) {
        return ApiResponse.<RegisterResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Đăng ký tài khoản thành công!")
                .data(authenticateService.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody RequestLogin request) {
        return ApiResponse.<LoginResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Đăng nhập thành công!")
                .data(authenticateService.login(request))
                .build();
    }
    @PostMapping("/logout")
    public ApiResponse<Void> logout() {
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Đăng xuất thành công!")
                .build();
    }

}

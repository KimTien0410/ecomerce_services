package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestLogin;
import com.rookies.ecomerce_services.dto.request.RequestRegister;
import com.rookies.ecomerce_services.dto.response.LoginResponse;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;

public interface AuthenticateService {
    public RegisterResponse register(RequestRegister request);
    public LoginResponse login(RequestLogin request);
    public void logout(String token);
}

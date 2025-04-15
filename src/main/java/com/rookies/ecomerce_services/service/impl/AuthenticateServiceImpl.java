package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestLogin;
import com.rookies.ecomerce_services.dto.request.RequestRegister;
import com.rookies.ecomerce_services.dto.request.RequestUser;
import com.rookies.ecomerce_services.dto.response.LoginResponse;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;
import com.rookies.ecomerce_services.dto.response.UserResponse;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.Role;
import com.rookies.ecomerce_services.entity.User;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.UserMapper;
import com.rookies.ecomerce_services.repository.UserRepository;
import com.rookies.ecomerce_services.service.AuthenticateService;
import com.rookies.ecomerce_services.service.RoleService;
import com.rookies.ecomerce_services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public RegisterResponse register(RequestRegister request) {
        User user = userMapper.registerToUser(request);
        if(userService.existsByEmail(user.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        Role role = roleService.getRoleByRoleName("USER");
        user.setRole(role);

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .user(user)
                .build();
        user.setCustomer(customer);
        userService.saveUser(user);
        return userMapper.registerToResponse(user);
    }

    @Override
    public LoginResponse login(RequestLogin request) {
        User user = userService.getByEmailLogin(request.getEmail());
        if (!user.getPassword().equals(request.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }
        return userMapper.toUserLogin(user);
    }

    @Override
    public void logout(String token) {
    }

}

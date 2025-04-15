package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestUser;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;
import com.rookies.ecomerce_services.dto.response.UserResponse;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.Role;
import com.rookies.ecomerce_services.entity.User;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.UserMapper;
import com.rookies.ecomerce_services.repository.RoleRepository;
import com.rookies.ecomerce_services.repository.UserRepository;
import com.rookies.ecomerce_services.service.RoleService;
import com.rookies.ecomerce_services.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public User getByEmailLogin(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public UserResponse updateUser(Long id, RequestUser request, MultipartFile file) {
        return null;
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return null;
    }

    @Override
    public Page<UserResponse> getAllUsers(int page, int size) {
        return null;
    }

    @Override
    public String deleteRestoreUser(Long id) {
        return "";
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}

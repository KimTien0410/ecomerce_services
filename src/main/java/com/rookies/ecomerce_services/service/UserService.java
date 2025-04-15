package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestUser;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;
import com.rookies.ecomerce_services.dto.response.UserResponse;
import com.rookies.ecomerce_services.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    public UserResponse updateUser(Long id, RequestUser request, MultipartFile file);
    public UserResponse getUserById(Long id);
    public UserResponse getUserByEmail(String email);
    public Page<UserResponse> getAllUsers(int page, int size);
    public String deleteRestoreUser(Long id);

    public User getById(Long id);
    public User getByEmailLogin(String email);
    public boolean existsByEmail(String email);
    public User saveUser(User user);
}

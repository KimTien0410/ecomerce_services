package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestAddAdmin;
import com.rookies.ecomerce_services.dto.request.RequestUpdateAdmin;
import com.rookies.ecomerce_services.dto.response.AdminResponse;
import com.rookies.ecomerce_services.entity.Admin;
import com.rookies.ecomerce_services.entity.Role;
import com.rookies.ecomerce_services.entity.User;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.AdminMapper;
import com.rookies.ecomerce_services.repository.AdminRepository;
import com.rookies.ecomerce_services.service.AdminService;
import com.rookies.ecomerce_services.service.RoleService;
import com.rookies.ecomerce_services.service.UserService;
import com.rookies.ecomerce_services.utils.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final CloudinaryService cloudinaryService;

    private AdminResponse toAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .adminId(admin.getAdminId())
                .email(admin.getUser().getEmail())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .avatar(admin.getAvatar())
                .role(admin.getUser().getRole().getRoleName())
                .build();
    }

    @Override
    public AdminResponse addAdmin(RequestAddAdmin request) {
        String email = request.getEmail();
        if (userService.existsByEmail(email)) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        String password = request.getPassword();
        Role role= roleService.getRoleByRoleName("ADMIN");
        User user=User.builder()
                .email(email)
                .password(password)
                .role(role)
                .build();
        Admin admin= Admin.builder()
                .user(user)
                .build();
        userService.saveUser(user);
        adminRepository.save(admin);
        return toAdminResponse(admin);
    }

    @Override
    public AdminResponse getAdminById(Long id) {
        Admin admin=getAdminByUserId(id);
        return toAdminResponse(admin);
    }
    @Override
    public AdminResponse updateAdmin(Long id, RequestUpdateAdmin request, MultipartFile file) {
        Admin admin =getAdminByUserId(id);
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
         if(firstName!=null){
             admin.setFirstName(firstName);
         }
        if(lastName!=null){
            admin.setLastName(lastName);
        }
        if(file != null && !file.isEmpty()) {
            if(admin.getAvatar() != null) {
                cloudinaryService.deleteOldFile(admin.getAvatar());
            }
            String imageUrl = cloudinaryService.uploadFile(file);
            admin.setAvatar(imageUrl);
        }
       adminRepository.save(admin);
        return toAdminResponse(admin);
    }

    @Override
    public Admin getAdminByUserId(Long userId) {
        User user = userService.getById(userId);
        return user.getAdmin();
    }
}

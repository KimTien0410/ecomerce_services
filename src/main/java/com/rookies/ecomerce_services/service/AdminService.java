package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestAddAdmin;
import com.rookies.ecomerce_services.dto.request.RequestUpdateAdmin;
import com.rookies.ecomerce_services.dto.response.AdminResponse;
import com.rookies.ecomerce_services.entity.Admin;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
    public Admin getAuthenticated();
    public  AdminResponse addAdmin(RequestAddAdmin request);
    public AdminResponse getAdminById(Long id);
    public AdminResponse updateAdmin(Long id, RequestUpdateAdmin request, MultipartFile file);
    public Admin getAdminByUserId(Long userId);
}

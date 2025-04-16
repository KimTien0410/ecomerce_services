package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestCustomer;
import com.rookies.ecomerce_services.dto.response.CustomerResponse;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    Customer findByUserId(Long id);
    Customer findByCustomerId(Long id);
    CustomerResponse toCustomerResponse(Customer customer);
    public CustomerResponse getProfile(Long userId);
    public void updateProfile(Long userId, RequestCustomer request, MultipartFile file);
    public String deleteAndRestore(Long Id);
    public Page<CustomerResponse> getAllCustomers(int page, int size, String sortBy, String sortDir, String search);
}

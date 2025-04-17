package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestCustomer;
import com.rookies.ecomerce_services.dto.response.CustomerResponse;
import com.rookies.ecomerce_services.entity.Admin;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.User;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.repository.CustomerRepository;
import com.rookies.ecomerce_services.service.AdminService;
import com.rookies.ecomerce_services.service.CustomerService;
import com.rookies.ecomerce_services.service.UserService;
import com.rookies.ecomerce_services.utils.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    @Override
    public Customer getAuthenticated() {
        return userService.getAuthenticatedUser().getCustomer();
    }

    @Override
    public Customer findByUserId(Long id) {
        User user = userService.getById(id);
        return user.getCustomer();
    }

    @Override
    public Customer findByCustomerId(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
    }

    @Override
    public CustomerResponse toCustomerResponse(Customer customer) {
        return  CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .email(customer.getUser().getEmail())
                .avatar(customer.getAvatar())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .roleName(customer.getUser().getRole().getRoleName())
                .createdOn(customer.getUser().getCreatedOn())
                .lastUpdatedOn(customer.getUser().getLastUpdatedOn())
                .build();
    }

    @Override
    public CustomerResponse getProfile () {
        Customer customer = getAuthenticated();
        return  CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .email(customer.getUser().getEmail())
                .avatar(customer.getAvatar())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .roleName(customer.getUser().getRole().getRoleName())
                .createdOn(customer.getUser().getCreatedOn())
                .lastUpdatedOn(customer.getUser().getLastUpdatedOn())
                .build();
    }
    @Transactional
    @Override
    public void updateProfile(RequestCustomer request, MultipartFile file) {
        Customer customer = getAuthenticated();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        if(file != null && !file.isEmpty()) {
            if(customer.getAvatar() != null) {
                cloudinaryService.deleteOldFile(customer.getAvatar());
            }
            String imageUrl = cloudinaryService.uploadFile(file);
            customer.setAvatar(imageUrl);
        }
        customerRepository.save(customer);
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(int page, int size, String sortBy, String sortDir, String search) {
        // Xác định hướng sắp xếp
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Customer> customers;
        if(search!=null){
            customers = customerRepository.searchCustomers(search, pageable);
        }
        else{
            customers = customerRepository.findAllWithUser(pageable);
        }
        return customers.map(this::toCustomerResponse);
    }

    @Override
    public String deleteAndRestore(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        User user = customer.getUser();

        if (user.isDeleted()) {
            user.setDeleted(false);
            customerRepository.save(customer);
            return "Khôi phục tài khoản thành công!";
        } else {
            user.setDeleted(true);
            customerRepository.save(customer);
            return "Xóa tài khoản thành công!";
        }
    }
}

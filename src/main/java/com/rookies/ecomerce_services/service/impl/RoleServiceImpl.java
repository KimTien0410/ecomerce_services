package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestRole;
import com.rookies.ecomerce_services.dto.response.RoleResponse;
import com.rookies.ecomerce_services.entity.Role;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.RoleMapper;
import com.rookies.ecomerce_services.repository.RoleRepository;
import com.rookies.ecomerce_services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse addRole(RequestRole request) {
        Role role=roleMapper.toRole(request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse updateRole(Long id, RequestRole request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        roleMapper.updateRole(role, request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleResponse getRoleByName(String name) {
        Role role = roleRepository.findByRoleName(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleResponseList(roles);
    }

    @Override
    public Role getRoleByRoleName(String name) {
        Role role = roleRepository.findByRoleName(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        return role;
    }
}

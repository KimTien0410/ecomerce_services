package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestRole;
import com.rookies.ecomerce_services.dto.response.RoleResponse;
import com.rookies.ecomerce_services.entity.Role;

import java.util.List;

public interface RoleService {
    public RoleResponse addRole(RequestRole request);
    public RoleResponse updateRole(Long id, RequestRole request);
    public RoleResponse getRoleById(Long id);
    public RoleResponse getRoleByName(String name);
    public List<RoleResponse> getAllRoles();

    public Role getRoleByRoleName(String name);

}

package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestRole;
import com.rookies.ecomerce_services.dto.response.RoleResponse;
import com.rookies.ecomerce_services.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RequestRole requestRole);

    void updateRole(@MappingTarget Role role, RequestRole requestRole);

    RoleResponse toRoleResponse(Role role);

    List<RoleResponse> toRoleResponseList(List<Role> roles);
}

package com.rookies.ecomerce_services.mapper;


import com.rookies.ecomerce_services.dto.request.RequestUpdateAdmin;
import com.rookies.ecomerce_services.dto.response.AdminResponse;
import com.rookies.ecomerce_services.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {
//    Admin toAdmin(RequestAddAdmin requestAdmin);
    AdminResponse toAdminResponse(Admin admin);

    void updateAdmin(@MappingTarget Admin admin, RequestUpdateAdmin requestUpdate);
}

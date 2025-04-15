package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestLogin;
import com.rookies.ecomerce_services.dto.request.RequestRegister;
import com.rookies.ecomerce_services.dto.request.RequestUser;
import com.rookies.ecomerce_services.dto.response.LoginResponse;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;
import com.rookies.ecomerce_services.dto.response.UserResponse;
import com.rookies.ecomerce_services.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerToUser(RequestRegister requestUser);
    RegisterResponse registerToResponse(User user);

    LoginResponse toUserLogin(User user);

    User toUser(RequestUser requestUser);

    void updateUser(@MappingTarget User user, RequestUser requestUser);

    UserResponse toUserResponse(User user);


}

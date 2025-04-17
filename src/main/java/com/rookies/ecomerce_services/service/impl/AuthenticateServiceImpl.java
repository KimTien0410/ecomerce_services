package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.configuration.jwt.JwtUtil;
import com.rookies.ecomerce_services.dto.request.RequestLogin;
import com.rookies.ecomerce_services.dto.request.RequestRegister;
import com.rookies.ecomerce_services.dto.request.RequestUser;
import com.rookies.ecomerce_services.dto.response.LoginResponse;
import com.rookies.ecomerce_services.dto.response.RegisterResponse;
import com.rookies.ecomerce_services.dto.response.UserResponse;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.Role;
import com.rookies.ecomerce_services.entity.User;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.UserMapper;
import com.rookies.ecomerce_services.repository.UserRepository;
import com.rookies.ecomerce_services.service.AuthenticateService;
import com.rookies.ecomerce_services.service.RoleService;
import com.rookies.ecomerce_services.service.TokenService;
import com.rookies.ecomerce_services.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService{
    private final UserService userService;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService userDetailService;




    @Override
    public RegisterResponse register(RequestRegister request) {
        User user = userMapper.registerToUser(request);
        if(userService.existsByEmail(user.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        Role role = roleService.getRoleByRoleName("USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .user(user)
                .build();
        user.setCustomer(customer);
        userService.saveUser(user);
        return userMapper.registerToResponse(user);
    }

    @Override
    public LoginResponse login(RequestLogin request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
            String jwt = jwtUtil.generateToken(userDetails);
            return LoginResponse.builder()
                    .accessToken(jwt)
                    .build();
        } catch (BadCredentialsException e) {
           throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    @Override
    public void logout(String token) {
        String jti= jwtUtil.extractId(token);
        Date expiredAt= jwtUtil.extractExpiration(token);
        tokenService.saveInvalidJwt(jti,expiredAt);
    }

}

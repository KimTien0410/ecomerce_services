package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.entity.InvalidToken;
import com.rookies.ecomerce_services.repository.InvalidTokenRepository;
import com.rookies.ecomerce_services.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final InvalidTokenRepository invalidTokenRepository;
    @Transactional
    @Override
    public void saveInvalidJwt(String jti, Date expiredAt) {
        InvalidToken invalidToken = InvalidToken.builder()
                .id(jti)
                .expiredAt(expiredAt)
                .build();
        invalidTokenRepository.save(invalidToken);
    }

    @Override
    public boolean isTokenInvalid(String jti) {
        return invalidTokenRepository.existsById(jti);
    }
}

package com.rookies.ecomerce_services.service;

import java.util.Date;

public interface TokenService {
    public void saveInvalidJwt(String jti, Date expiredAt);
    public boolean isTokenInvalid(String jti);
}

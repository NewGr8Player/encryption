package com.xavier.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTVars {

    /**
     * Tocken 超时时间
     */
    public static Long EXPIRE_TIME;

    /**
     * 秘钥
     */
    public static String SECRET;

    @Value("${jwt.expire_time}")
    public void setExpireTime(Long expireTime) {
        EXPIRE_TIME = expireTime;
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        SECRET = secret;
    }
}

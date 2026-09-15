package com.tiamo.dt.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * JWT工具类
 **/
public class JwtUtil {

    // Token过期时间30分钟
    public static final long EXPIRE_TIME = 4 * 60 * 60 * 1000;

    // Reuqest Header中token的key
    public static final String AUTH_TOKEN_KEY = "X-Access-Token";

    /**
     * 校验token是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @param secret   用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);

    }

    /**
     * 根据token获取用户账号
     *
     * @param accessToken
     * @return username
     */
    public static String getUserName(String accessToken) {
        try {
            DecodedJWT jwt = JWT.decode(accessToken);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取用户账号
     *
     * @param accessToken
     * @return username
     */
    public static Long getUserId(String accessToken) {
        try {
            DecodedJWT jwt = JWT.decode(accessToken);
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 判断token是否已过期
     *
     * @param accessToken
     * @return true-过期,false-没过期
     */
    public static Boolean isTokenExpired(String accessToken) {
        DecodedJWT jwt = JWT.decode(accessToken);
        return jwt.getExpiresAt().getTime() < new Date(System.currentTimeMillis()).getTime();
    }

}

package com.tencent.wxcloudrun.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类
 *
 * @author Ray。
 * @create 2020-03-14 16:24
 */
public class JwtUtils {

    //发行者
    public static final String SUBJECT = "raychen";
    //过期时间 一天
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //密钥
    public static final String APPSECRET = "raychen11";

    /**
     * 生成jwt
     *
     * @param userId
     * @return
     */
    public static String geneJsonWebToken(Long userId, String username) {
        if (userId == null) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)
                .claim("id", userId)
                .claim("name", username)
//                .claim("password", user.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
    }

    /**
     * 校验token 用户信息可在claims中获取
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        //仿造的token或者已过期就会报错
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            System.out.println("catch...");
        }
        return null;
    }
}
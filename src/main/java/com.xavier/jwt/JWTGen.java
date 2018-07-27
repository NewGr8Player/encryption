package com.xavier.jwt;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xavier.bean.Auth;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT生成
 *
 * @author NewGr8Player
 */
@Component
public class JWTGen {

	/**
	 * 生成 token
	 *
	 * @param auth 鉴权信息
	 * @return 加密的token
	 */
	public String createToken(Auth auth) {
		try {
			Date date = new Date(System.currentTimeMillis() + JWTVars.EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(JWTVars.SECRET);
			return JWT.create()
					/* 附带auth信息 */
					.withClaim("auth", JSON.toJSONString(auth))/* 载荷部分包含的信息 */
					/* 到期时间 */
					.withExpiresAt(date)
					/* 创建一个新的JWT，并使用给定的算法进行标记 */
					.sign(algorithm);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}


}

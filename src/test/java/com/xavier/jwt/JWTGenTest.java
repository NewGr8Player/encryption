package com.xavier.jwt;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xavier.Application;
import com.xavier.bean.Auth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JWTGenTest {

	/**
	 * 校验 token 是否正确
	 *
	 * @param token    密钥
	 * @param auth 鉴权信息
	 * @return 是否正确
	 */
	public boolean verify(String token, Auth auth) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWTVars.SECRET);
			/* 在token中附带了username信息 */
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("auth", JSON.toJSONString(auth))
					.build();
			/* 验证 token */
			verifier.verify(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * 获得token中的信息，无需secret解密也能获得
	 *
	 * @return token中包含的用户名
	 */
	public Auth getAuth(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return JSON.parseObject( jwt.getClaim("auth").asString(),Auth.class);
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	@Autowired
	private JWTGen jwtGen;

	@Test
	public void testAuth(){
		Auth auth = new Auth("testSystem","www.abc.com");
		String token = jwtGen.createToken(auth);
		System.out.println(getAuth(token).equals(auth));
		System.out.println(verify(token,auth));
	}
}

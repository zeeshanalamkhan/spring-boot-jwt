package com.zeeshan.jwt.constants;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final Long EXPIRATION_TIME = 864_000_000L;
	public static final String HEADER_STRING = "authorization";
	public static final String SIGN_UP_URL = "/sign-up";

}

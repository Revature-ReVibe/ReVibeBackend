package com.ReVibe.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ReVibe.model.Account;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String secret;
	private int jwtExpirationInMs;
	public String getSecret() {
		return secret;
	}
	
	@Value("{$jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public int getJwtExpirationInMs() {
		return jwtExpirationInMs;
	}

	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}
	
	
	public String generateToken (UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<>();
		
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		
		if(roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUSer", true);
		}
	return doGenerateToken(claims, userDetails.getUsername()); 
		
	}
	
	private String doGenerateToken (Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
}

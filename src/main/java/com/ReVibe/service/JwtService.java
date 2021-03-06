package com.ReVibe.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
private static String SECRET_KEY = "${jwt.secret}";
	
        /**
         * This method creates a Java Web Token
         * @param id            the String representing the id
         * @param issuer        the String representing the issuer
         * @param subject       the String representing the subject
         * @param ttlMillis     the long representing the time in milliseconds
         * @return              the String of the serialized java web token
         */
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
                long nowMillis = System.currentTimeMillis();
                Date now = new Date(nowMillis);

                byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

                Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

                JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);

                //if it has been specified, let's add the expiration
                if (ttlMillis >= 0) {
                    long expMillis = nowMillis + ttlMillis;
                    Date exp = new Date(expMillis);
                    builder.setExpiration(exp);
                }//if time Milliseconds is greater than or equal to 0

                //Builds the JWT and serializes it to a compact, URL-safe string
                return builder.compact();
	}//createJWT(String, String, String, long)
	
        /**
         * This method decodes the java web token
         * @param jwt   the String representing the java web token to be decoded
         * @return      the Claim object decoded from the java web token;
         *              May be <code>null</code>.
         */
	public static Claims decodeJWT(String jwt) {
		try {
                    Claims claim = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(jwt).getBody();
                    return claim;
		}catch(io.jsonwebtoken.SignatureException e) { 
			return null;
		}catch(io.jsonwebtoken.ExpiredJwtException e) {
			return null;
		}//catch
	}//decodeJWT(String)
}//JwtService

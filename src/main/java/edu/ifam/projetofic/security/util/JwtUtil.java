package edu.ifam.projetofic.security.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String GenerateToken(String cpf) {
		
		return Jwts.builder()
				.setSubject(cpf)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public boolean ValidateToken(String token) {
		
		Claims claims = getClaims(token);
		
		if(claims != null) {
			
			String cpf = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
		
			if(cpf != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getCpf(String token) {
		
		Claims claims = getClaims(token);
		
		if(claims != null) {
			return claims.getSubject();
		}
		
		return null;
	}
	
	private Claims getClaims(String token) {
		
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch(Exception e) {
			
			return null;
		}
	}
}

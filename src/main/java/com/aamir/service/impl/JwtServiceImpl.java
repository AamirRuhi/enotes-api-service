package com.aamir.service.impl;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.aamir.entity.User;
import com.aamir.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{
	private String secretKey = "";

	public JwtServiceImpl() {
		try {
			//key generate ke liye algorithm use krenge  HmacSHA256 ,encode krenge Base64 ke through ,decocord bhi Base64 ke throught krenge
			//KeyGenerator class h,applicn run hone ke baad fir se secret ket generate krna h
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			//encord krke secretKey me assign krlo
			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//is method ko call krenge userserviceimpl me
	@Override
	public String generateToken(User user) {
        //is claims object ko pass krdena h niche
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("role", user.getRoles());
		claims.put("status", user.getStatus().isActive());
		
        //pehle token lenge 
		String token = Jwts.builder()
				.claims().add(claims)//claims add krna h
				.subject(user.getEmail())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60* 60 * 10))
				.and()
				.signWith(getKey())// getKey method bnalenge 
				.compact();

		return token;
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
// generateToken method ke baad check krenge postman se ki token genereate ho rha h ki nhi token ko jwt io me check kr lenge sara details milega
	
	
	
	
	
	
	
	
}

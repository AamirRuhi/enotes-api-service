package com.aamir.service.impl;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.aamir.entity.User;
import com.aamir.exception.JwtTokenExpiredException;
import com.aamir.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
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
				.signWith(getKey())// getKey method bnalenge ,jitni baar applicn run hoga new token generate hoga
				.compact();

		return token;
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
// generateToken method ke baad check krenge postman se ki token genereate ho rha h ki nhi token ko jwt io me check kr lenge sara details milega
	
	
	
	
	@Override
	public String extractUsername(String token) {
		// 1->extractAllClaims name ka ek method bnayenge usme token pass kr dunga to claims ka object milega
		Claims claims = extractAllClaims(token);
		return claims.getSubject();//yaha se hume email mil jayega qki subject me uper email set kiya tha
	}
	

	// 1->yaha  bna liya extractAllClaims method userwala
	private Claims extractAllClaims(String token) {
		try {
		return Jwts.parser()
				//2->token encripted hai ab usse decript krenge to verifyWith ke andr decrytKey method bnayenge useme secretKey pass krna hai
				.verifyWith(decrytKey(secretKey))
				.build().parseSignedClaims(token).getPayload();//claims object direct return kr diya ,ab decrytKey ispe aa gye
		}
		catch (ExpiredJwtException e) {
			//JwtTokenExpiredException bnalo exception pakage me
			throw new JwtTokenExpiredException("Token is Expired");
			
		}catch (JwtException e) {
			//agr token me kuch problem hai
			throw new JwtTokenExpiredException("Invalid Jwt token");
			
		}catch (Exception e) {
			//iske alawa koi bhi exception aye to direct throw krlo
			throw e;
		}
	}
	
	
//2-> yaha bn gya decrytKey method uper wala , an decrypt krenge secretKey ko
	private SecretKey decrytKey(String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes); //SecretKey ka object return krne wala h
	}

	//token se role kaise milega pehle claims ka object get krenge usse role milega, similarly status,id bhi get kr skte hai
	public String role(String token)
	{
		Claims claims = extractAllClaims(token);
		String role=(String)claims.get("role");
		//key jo map me diya tha objct mila use string me convert kr liya
		return role;
	}
	

	
	
	
	//3->validation ke liye
	@Override
	public Boolean validateToken(String token, UserDetails userDetails) {
        //token se jo username aa rha hai use compare krenge jo db me hai ki nhi ,tken expire bhi check krenge 
		String username = extractUsername(token);
		//4->ab isTokenExpired naam ka method bnayenge usme check kreng ki token expired h ki nhi 
		Boolean isExpired=isTokenExpired(token);//ab isTokenExpired method pr jayenge
		//username jo header me de rhe h or userDetails ka username jo db me h compare kr rhe hai
		if(username.equalsIgnoreCase(userDetails.getUsername()) && !isExpired)
		{
			return true;
		}
		return false;
	}
     //4->yah isTokenExpired method bna
	private Boolean isTokenExpired(String token) {
		//claims ka object se hume expiration date mil jayega
		Claims claims = extractAllClaims(token);
		Date expiredDate = claims.getExpiration();
		// 10th dec - today - expir- 11th dec comapring expiredDate with todays date
		return expiredDate.before(new Date());
		//validateToken method me condition lgayenge username,expiration date ke based pr
	}
//ab ye pura hone ke baad class bnayenge JwtFilter security pakage ke andr bnayge 
	//or extend krenge 1->onceperrequesrt filter hai aur 2->Generic filterFilterBean bhi hai ,unimplent dofilter method
	//note-->koi bhi api acess krne se pehle ye filter first call hoga ,then controller pr jayega
	
	
	
	
	
}

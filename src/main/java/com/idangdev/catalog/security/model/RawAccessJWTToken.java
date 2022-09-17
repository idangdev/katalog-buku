package com.idangdev.catalog.security.model;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

//kelas ini digunakan sebagai representasi dari token yag dikirimkan oleh client kepada backend


public class RawAccessJWTToken implements Token { 
	
	//untuk menampung JWT yang di inputkan oleh CLIENT
	private String token; 
	
	public RawAccessJWTToken(String token) {
		super();
		this.token = token;
	}
	
	// methos yang dgnkn untuk memparsing token JWT yang diberikan CLIENT tersebut yang ada di field line 15 nantinya akan di parsing menjadi CLAIMS
	public Jws<Claims> parseClaims(Key signingKey){
		return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(this.token);
	}

	@Override
	public String getToken() {
		return this.token;
	}	
	
	

}

package com.idangdev.catalog.security.model;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccessJWTToken implements Token{

	private final String rawToken; // token yang masih mentah (belum kita decode) / token yang benar2 berasal dari CLIENT
	
	private Claims claims; // berisi statement yang memberikan informasi berupa entitas biasanya terkait dengan informasi dari user
	
	@Override
	public String getToken() {
		return this.rawToken;
	}

}

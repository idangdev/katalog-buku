package com.idangdev.catalog.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtAuthenticationToken extends AbstractAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 94222890082295455L;

	private RawAccessJWTToken rawAccessJWTToken; // untuk credentials nya
	
	private UserDetails userDetails; // untuk principal nya
	
	// constructor ini digunakan untuk membuat object JWTAuthenticationToken dengan token jwt yang blm di authentikasi
	public JwtAuthenticationToken(RawAccessJWTToken rawAccessJWTToken) {
		super(null);
		this.rawAccessJWTToken = rawAccessJWTToken;
		this.setAuthenticated(false);
	}

	// constructor ini digunakan untuk membuat object JWTAuthenticationToken dengan kasus token jwt nya tsb sudah di validasi (authentikasi)
	// dan kita meng assign userDetails dan authoritiesnya
	public JwtAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials(); // untuk menghapus credentials nya
		this.userDetails = userDetails;
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.rawAccessJWTToken;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.userDetails;
	}
	
	@Override
	public void eraseCredentials() {
		// TODO Auto-generated method stub
		super.eraseCredentials();
		this.rawAccessJWTToken = null;
	}

}

package com.idangdev.catalog.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.idangdev.catalog.service.AppUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider{

	private final AppUserService appUserService;
	
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// authenticate = untuk menambahkan logic dari proses autentication nya
		
		// mengambil username dan password nya melalui argumen Authentication
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		// mengambil data user yang ada di database
		UserDetails appUser = appUserService.loadUserByUsername(username);
		// cek password nya sesuai apa tidak
		if (!passwordEncoder.matches(password, appUser.getPassword())) {
			throw new BadCredentialsException("invalid.username.password");
		}
		
		return new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// supports = untuk menunjukkan AuthenticationProvider mana yang ingin digunakan berdasarkan jenis Token nya
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

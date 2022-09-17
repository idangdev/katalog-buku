package com.idangdev.catalog.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.idangdev.catalog.dto.UserDetailResponseDTO;

public interface AppUserService extends UserDetailsService {
	
	public UserDetailResponseDTO findUserDetail();

}

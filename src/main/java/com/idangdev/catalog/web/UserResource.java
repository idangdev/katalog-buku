package com.idangdev.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idangdev.catalog.dto.UserDetailResponseDTO;
import com.idangdev.catalog.service.AppUserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserResource {

	private final AppUserService appUserService;
	
	@GetMapping("/v1/user")
	public ResponseEntity<UserDetailResponseDTO> findUserDetail(){
		return ResponseEntity.ok(appUserService.findUserDetail());
	}
	
}

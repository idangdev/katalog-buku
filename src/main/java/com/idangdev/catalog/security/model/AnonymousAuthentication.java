package com.idangdev.catalog.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AnonymousAuthentication extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1061406724276167226L;

	public AnonymousAuthentication() {
		super(null);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

}

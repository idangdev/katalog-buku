package com.idangdev.catalog.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.idangdev.catalog.security.model.AnonymousAuthentication;
import com.idangdev.catalog.security.model.JwtAuthenticationToken;
import com.idangdev.catalog.security.model.RawAccessJWTToken;
import com.idangdev.catalog.security.util.TokenExtractor;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	private final TokenExtractor tokenExtractor;
	
	private final AuthenticationFailureHandler failureHandler;
	
	
	
	public JWTAuthenticationFilter(TokenExtractor tokenExtractor, 
			AuthenticationFailureHandler failureHandler,
			RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		this.failureHandler = failureHandler;
		this.tokenExtractor = tokenExtractor;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String tokenPayload = request.getHeader("Authorization");	//bearer xxx.yyy.zzz
		RawAccessJWTToken token = new RawAccessJWTToken(tokenExtractor.extract(tokenPayload));
		return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(authResult);
			SecurityContextHolder.setContext(context);
			chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());
		failureHandler.onAuthenticationFailure(request, response, failed);
	}

}

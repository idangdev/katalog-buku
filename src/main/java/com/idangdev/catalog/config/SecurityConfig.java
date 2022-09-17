package com.idangdev.catalog.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idangdev.catalog.security.filter.JWTAuthenticationFilter;
import com.idangdev.catalog.security.filter.UsernamePasswordAuthenticationFilter;
import com.idangdev.catalog.security.provider.JwtAuthenticationProvider;
import com.idangdev.catalog.security.provider.UsernamePasswordAuthProvider;
import com.idangdev.catalog.security.util.SkipPathRequestMatcher;
import com.idangdev.catalog.security.util.TokenExtractor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String V1_URL = "/v1/**";	// semua alamat yang di awali dengan /v1
	
	private static final String V2_URL = "/v2/**";	// semua alamat yang di awali dengan /v2

//	@Autowired
//	private AppUserService appUserService;
	
	@Autowired
	private AuthenticationFailureHandler failureHandler;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
	
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TokenExtractor tokenExtractor;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(usernamePasswordAuthProvider);
		auth.authenticationProvider(jwtAuthenticationProvider);
	}
	
	protected JWTAuthenticationFilter  buildJwtAuthFilter(List<String> pathToSkip, List<String> patternList) {	// pathToSkip (URL yang di skip / yg tidak perlu melalui athentikasi JWT), pattertList itu kebalikan dari pathToSkip
		SkipPathRequestMatcher matches = new SkipPathRequestMatcher(pathToSkip, patternList);
		JWTAuthenticationFilter filter = new JWTAuthenticationFilter(tokenExtractor, failureHandler, matches);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
		// ant matcher
	}
	
	// method untuk meng construct filter yang telah kita buat sebelumnya
	protected UsernamePasswordAuthenticationFilter buildUsernamePasswordAuthFilter(String loginEntiryPoint) {
		UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter(loginEntiryPoint, successHandler, failureHandler, objectMapper);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<String> permitAllEndpointList = Arrays.asList(
				"/v1/login"
				);
		
		List<String> authenticatedEndpointList = Arrays.asList(
				V1_URL,
				V2_URL
				);
		// saya ingin semua request itu di autentikasi
		http.authorizeRequests().antMatchers(V1_URL, V2_URL).authenticated()
		.and()					// menambahkan konfigurasi lain
		.csrf().disable()		// disable csrf karena disini yang kita bangun adalah rest API. dan tidak perlu csrf
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		// menambahkan session management
		.and()					// menambahkan konfigurasi lain
//		.httpBasic();			// menambahkan httpBasic
		.addFilterBefore(buildUsernamePasswordAuthFilter("/v1/login"), org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(buildJwtAuthFilter(permitAllEndpointList, authenticatedEndpointList), UsernamePasswordAuthenticationFilter.class);
	}
}

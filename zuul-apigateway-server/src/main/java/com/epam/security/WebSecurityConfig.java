package com.epam.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtTokenUtil jwtConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilterAfter(
						new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public JwtTokenUtil jwtConfig() {
		return new JwtTokenUtil();
	}

}
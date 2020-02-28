package com.epam.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtConfig;

	public JwtTokenAuthenticationFilter(JwtTokenUtil jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("chala");
		String header = request.getHeader(jwtConfig.getHeader());
		System.out.println(header);
		if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
			chain.doFilter(request, response);
			System.out.println("header ke andar chala");
			return;
		}
		System.out.println("bahar");
		String token = header.replace(jwtConfig.getPrefix(), "");
		System.out.println(token);
		try {
			System.out.println("andar chala");
			Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
			System.out.println(claims);
			String username = claims.getSubject();
			if (username != null) {
				@SuppressWarnings("unchecked")

				List<String> authorities = (List<String>) claims.get("authorities");
				System.out.println(authorities);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				System.out.println("aya");
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
		}
		chain.doFilter(request, response);
	}

}
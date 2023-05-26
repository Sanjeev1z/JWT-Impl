package com.youtube.jwt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.youtube.jwt.service.impl.JwtServiceImpl;
import com.youtube.jwt.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;  
	
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = request.getHeader("Authorization");
		String jwtToken = null;
		String username = null;
		
		if(header!=null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			
			try {
				username = jwtUtil.getUserNameFromToken(jwtToken);				
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get JWT token");
			}
			catch(ExpiredJwtException e) {
				System.out.println("JWT token is expired");
			}
		}		
		else {
			System.out.println("Invalid token, not start with Bearer string ");
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtServiceImpl.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));				
				SecurityContextHolder.getContext().setAuthentication(authToken);				
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
}

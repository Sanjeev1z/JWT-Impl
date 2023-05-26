package com.youtube.jwt.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.youtube.jwt.dtos.JwtRequest;
import com.youtube.jwt.dtos.JwtResponse;
import com.youtube.jwt.entities.User;
import com.youtube.jwt.exceptions.JwtImplBusinessException;
import com.youtube.jwt.repositories.UserRepository;
import com.youtube.jwt.util.JwtUtil;

@Service
public class JwtServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		User user = findUserById(username);		
		
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getUserPassword(), getAuthorities(user));
		
	}
	
	private Set getAuthorities(User user) {
		Set authorities = user.getRole().stream()
			.map( role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
			.collect(Collectors.toSet());
		
		return authorities;
	}
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) {
		String username = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		
		authenticate(username,userPassword);		
		
		final UserDetails userDetails = loadUserByUsername(username);	
		
		String newGeneratedToken = jwtUtil.generateToken(userDetails);		
		User user = findUserById(username);
		
		return new JwtResponse(user, newGeneratedToken); 
		
	}
	 
	private void authenticate(String username, String userPassword) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPassword));
		}
		catch(DisabledException e) {
			throw new JwtImplBusinessException("User is disabled");
		}
		catch (BadCredentialsException e) {
			throw new JwtImplBusinessException("Bad credentials from user");
		}
	}
	
	private User findUserById(String username) {
		Optional<User> entity = this.userRepository.findById(username);
		if (!entity.isPresent()) {
				throw new UsernameNotFoundException("User name is not valid: " + username);
		}
		
		return entity.get();
	}
}

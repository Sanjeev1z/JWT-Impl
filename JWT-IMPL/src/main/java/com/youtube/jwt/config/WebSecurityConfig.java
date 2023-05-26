package com.youtube.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private JwtAuthEntryPoint authEntryPoint;

	@Autowired
	private JwtAuthRequestFilter jwtAuthRequestFilter;

	@Autowired
	private UserDetailsService jwtService;

	/*
	 * @Bean
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception { // TODO Auto-generated method stub return return
	 * super.authenticationManagerBean(); }
	 */
	

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.cors(); http.csrf().disable()
	 * .authorizeRequests().antMatchers("/initDummy").permitAll()
	 * .antMatchers(HttpHeaders.ALLOW).permitAll() .anyRequest().authenticated()
	 * .and() .exceptionHandling().authenticationEntryPoint(authEntryPoint) .and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * http.addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class); }
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors().disable();
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/api/jwt/authenticate","/api/user/initDummy","/api/user/registerNewUser")
		.permitAll()
		.antMatchers(HttpHeaders.ALLOW).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthRequestFilter, UsernamePasswordAuthenticationFilter.class);

		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain build = http.build();

		return build;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder
	 * authenticationManagerBuilder) throws Exception {
	 * authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(
	 * passwordEncoder()); }
	 */
	 

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(jwtService);
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}
}

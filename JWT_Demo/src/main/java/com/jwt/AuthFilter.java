package com.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.Security.CustomUserDetailsService;
import com.jwt.hlper.JwtUtil;

@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String requestTokenHeader = request.getHeader("Authorization");
		String usernameString =null;
		String jwtToken = null;
		
		//null and format
		if (requestTokenHeader != null && requestTokenHeader.startsWith("bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				
				usernameString = this.jwtUtil.extractUsername(jwtToken);
		
			} catch (Exception e) {
				// TODO: handle exception
			}
			
	
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(usernameString);
		
		//security
		if (usernameString != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			
			UsernamePasswordAuthenticationToken authenticationToken = 
			new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
		}else {
			System.out.println("token not validated");
		}
		
	
	   }
	
		filterChain.doFilter(request, response);
	
	}
}

package com.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.Security.CustomUserDetailsService;
import com.jwt.hlper.JwtUtil;
import com.jwt.model.JwtREquest;
import com.jwt.model.JwtResponce;

@RestController
public class JwtController {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token" ,method = RequestMethod.POST)
	public ResponseEntity<?>genrateToken(@RequestBody JwtREquest jEquest) throws Exception{
		System.out.println(jEquest);
		
		try {
			
			this.authenticationManager.authenticate
			(new UsernamePasswordAuthenticationToken(jEquest.getUsername(), jEquest.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("bad credentials");
		}catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("bad credentials");
		}
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jEquest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("token  : "+token);
		return ResponseEntity.ok(new JwtResponce(token));
		
	}
}

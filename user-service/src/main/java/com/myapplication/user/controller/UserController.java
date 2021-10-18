package com.myapplication.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapplication.bean.CommonResponse;
import com.myapplication.bean.Response;
import com.myapplication.user.config.JwtTokenUtil;
import com.myapplication.user.model.User;
import com.myapplication.user.service.impl.JwtUserDetailsService;

@RestController
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
	public Response<String> listProductCategory(@RequestBody User req) {
		Response<String> resp = new Response<>();
		try {
			authenticate(req.getUsername(), req.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			resp.setData(token);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response<?> saveUser(@RequestBody User user) throws Exception {
		Response<?> resp = new Response<>();
		try {
			userDetailsService.save(user);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public Response<User> getUser(@RequestBody User user) throws Exception {
		Response<?> resp = new Response<>();
		try {
			userDetailsService.save(user);
			resp.setResponse(CommonResponse.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			resp.setResponse(CommonResponse.UNDEFINED_ERROR);
		}
		return resp;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}

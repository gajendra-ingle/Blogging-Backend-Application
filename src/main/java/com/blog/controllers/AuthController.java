package com.blog.controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.JwtAuthRequest;
import com.blog.dto.JwtAuthResponse;
import com.blog.dto.UserDTO;
import com.blog.entities.User;
import com.blog.expections.ApiException;
import com.blog.repositories.UserRepository;
import com.blog.security.JwtTokenHelper;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	// LOGIN API
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		logger.info("Login attempt for username: {}", request.getUsername());

		authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, UserDTO.class));

		logger.info("Token generated successfully for user: {}", request.getUsername());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// AUTHENTICATION METHOD
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {
			authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			logger.warn("Invalid login attempt for user: {}", username);
			throw new ApiException("Invalid username or password !!");
		}
	}

	// REGISTER API
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDto) {
		logger.info("Registering new user: {}", userDto.getEmail());
		UserDTO registeredUser = userService.registerNewUser(userDto);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}

	// GET CURRENT LOGGED-IN USER
	@GetMapping("/current-user/")
	public ResponseEntity<UserDTO> getUser(Principal principal) {
		String email = principal.getName();
		logger.info("Fetching current user details for: {}", email);

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		return new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.OK);
	}
}
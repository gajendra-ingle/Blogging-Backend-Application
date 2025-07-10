package com.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Trying to load user with email: {}", username);

		return userRepository.findByEmail(username)
	            .orElseThrow(() -> {
	                logger.warn("User not found: {}", username);
	                return new UsernameNotFoundException("User not found with email: " + username);
	            });
	}

}

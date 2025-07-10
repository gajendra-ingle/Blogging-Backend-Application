package com.blog.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.config.AppConstants;
import com.blog.dto.UserDTO;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.expections.ResourceNotFoundException;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User user = dtoToUser(userDto);
		User savedUser = userRepository.save(user);
		return usersToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {

		User finduser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

		finduser.setName(userDto.getName());
		finduser.setEmail(userDto.getEmail());
		finduser.setPassword(userDto.getPassword());
		finduser.setAbout(userDto.getAbout());

		User updatedUser = userRepository.save(finduser);
		UserDTO userDTO1 = usersToDTO(updatedUser);
		return userDTO1;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		return usersToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userdto = users.stream().map(user -> usersToDTO(user)).collect(Collectors.toList());
		return userdto;
	}

	@Override
	public void deletUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));

		userRepository.delete(user);
	}

	// Conversion DTO to user or vice versa
	private User dtoToUser(UserDTO userDto) {
		return modelMapper.map(userDto, User.class);
	}

	private UserDTO usersToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);

		// Encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// Assign default role
		Optional<Role> optionalRole = roleRepository.findById(AppConstants.NORMAL_USER);
		if (optionalRole.isEmpty()) {
			throw new ResourceNotFoundException("Role", "id", AppConstants.NORMAL_USER);
		}

		Role role = optionalRole.get();
		user.getRoles().add(role);

		User savedUser = userRepository.save(user);

		return modelMapper.map(savedUser, UserDTO.class);
	}

}

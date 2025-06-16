package com.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.UserDTO;
import com.blog.entities.User;
import com.blog.expections.ResourceNotFoundException;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

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

}

package com.blog.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDTO {

	private Integer id;

	@NotEmpty
	@Size(min = 4, message = "Username must be greater than 4 characters!")
	private String name;

	@Email(message = "Email address not valid!")
	private String email;

	@NotEmpty(message = "Password cannot be empty!")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", message = "Password must be 8–20 characters long, include upper and lower case letters, a number, and a special character (@#$%^&+=!), and contain no spaces.")
	private String password;

	@NotBlank(message = "About section cannot be empty.")
	@Size(max = 500, message = "About section cannot exceed 500 characters.")
	private String about;

	private Set<RoleDTO> roles = new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

}

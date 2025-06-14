package com.blog.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_table")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@NotBlank(message = "Post title is required")
	@Size(max = 100, message = "Title can be up to 100 characters long")
	@Column(name = "post_title", length = 100, nullable = false)
	private String postTitle;

	@NotBlank(message = "Post content cannot be blank")
	@Column(name = "post_content", length = 10000)
	private String postContent;

	@Size(max = 255, message = "Image name can be up to 255 characters")
	@Column(name = "image")
	private String postImageName;

	@NotNull(message = "Post date is required")
	@PastOrPresent(message = "Post date can't be in the future")
	@Column(name = "date")
	private Date postAddedData;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Category is required")
	private Category category;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull(message = "User is required")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

}

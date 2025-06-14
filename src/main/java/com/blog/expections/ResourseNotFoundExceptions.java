package com.blog.expections;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourseNotFoundExceptions extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;

	public ResourseNotFoundExceptions(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}

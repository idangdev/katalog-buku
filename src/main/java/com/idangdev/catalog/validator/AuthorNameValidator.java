package com.idangdev.catalog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.idangdev.catalog.validator.annotation.ValidAuthorName;

@Component
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName, String> {

	@Override
	public boolean isValid(String authorName, ConstraintValidatorContext context) {
		return !authorName.equalsIgnoreCase("Tedy");	// Jika Tedy return FALSE
	}

}

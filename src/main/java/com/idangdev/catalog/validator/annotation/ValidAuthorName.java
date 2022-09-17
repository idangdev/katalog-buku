package com.idangdev.catalog.validator.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.idangdev.catalog.validator.AuthorNameValidator;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = AuthorNameValidator.class)
public @interface ValidAuthorName {
	
	String message() default "author name invalid";

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
